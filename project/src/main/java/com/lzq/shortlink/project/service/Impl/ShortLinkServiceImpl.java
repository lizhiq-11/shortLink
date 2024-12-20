package com.lzq.shortlink.project.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzq.shortlink.project.dao.entity.ShortLinkDO;
import com.lzq.shortlink.project.dao.mapper.ShortLinkMapper;
import com.lzq.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.lzq.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.lzq.shortlink.project.service.ShortLinkService;
import com.lzq.shortlink.project.toolkit.HashUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 短链接接口实现层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {
    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        String shortLinkSuffix = generateSuffix(requestParam);
        ShortLinkDO shortLinkDO = BeanUtil.toBean(requestParam, ShortLinkDO.class);
        shortLinkDO.setShortUri(shortLinkSuffix);
        shortLinkDO.setEnableStatus(0);
        shortLinkDO.setFullShortUrl(requestParam.getDomain() + "/" + shortLinkSuffix);//域名+"/"+短链接
        baseMapper.insert(shortLinkDO);
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .gid(requestParam.getGid())
                .originUrl(requestParam.getOriginUrl())
                .build();
    }
    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
        String originUrl = requestParam.getOriginUrl();
        return HashUtil.hashToBase62(originUrl);//通过hash函数转化为62进制
    }
}
