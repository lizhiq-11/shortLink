package com.lzq.shortlink.project.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzq.shortlink.project.common.convention.exception.ServiceException;
import com.lzq.shortlink.project.dao.entity.ShortLinkDO;
import com.lzq.shortlink.project.dao.mapper.ShortLinkMapper;
import com.lzq.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.lzq.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.lzq.shortlink.project.service.ShortLinkService;
import com.lzq.shortlink.project.toolkit.HashUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * 短链接接口实现层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {

    private final RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;
    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        String shortLinkSuffix = generateSuffix(requestParam);
        String fullShortUrl = requestParam.getDomain() + "/" + shortLinkSuffix;//域名+"/"+短链接
        ShortLinkDO shortLinkDO = BeanUtil.toBean(requestParam, ShortLinkDO.class);
        shortLinkDO.setShortUri(shortLinkSuffix);
        shortLinkDO.setEnableStatus(0);
        shortLinkDO.setFullShortUrl(fullShortUrl);
        try {
            baseMapper.insert(shortLinkDO);
        } catch (DuplicateKeyException ex) {
            log.warn("短链接：{} 重复入库",fullShortUrl);
            throw new ServiceException("短链接生成重复");
        }
        shortUriCreateCachePenetrationBloomFilter.add(shortLinkSuffix);
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .gid(requestParam.getGid())
                .originUrl(requestParam.getOriginUrl())
                .build();
    }
    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
        int customGenerateCount = 0;
        String shortUri;
        while(true){
            if(customGenerateCount > 10){
                throw new ServiceException("短链接频繁生成，请稍后重试");
            }
            String originUrl = requestParam.getOriginUrl();
            shortUri = HashUtil.hashToBase62(originUrl);//通过hash函数转化为62进制
            if(shortUriCreateCachePenetrationBloomFilter.contains(shortUri)){
                break;
            }
            customGenerateCount++;
        }
        return shortUri;
        //看似没有问题，当数据量大的时候会出现冲突，冲突就要重试，避免重试导致数据库压力过大还要设置最大重试次数，如果每次都用baseMapper.select
        //查数据库，则不会支持海量并发，此时就要走缓存，但是如果用hash会存在大KEY问题，用string会存在大字符串问题，所以布隆过滤器为最优解
        // 首先判断是否存在布隆过滤器，如果不存在直接新增
    }
}
