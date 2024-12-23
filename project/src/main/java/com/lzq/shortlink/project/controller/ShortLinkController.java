package com.lzq.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzq.shortlink.project.common.convention.result.Result;
import com.lzq.shortlink.project.common.convention.result.Results;
import com.lzq.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.lzq.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.lzq.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.lzq.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.lzq.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.lzq.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.lzq.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短链接控制层
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private  final ShortLinkService shortLinkService;

    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> create(@RequestBody ShortLinkCreateReqDTO requestParam){
        shortLinkService.createShortLink(requestParam);
        return Results.success(null);
    }

    @PutMapping("/api/short-link/v1/uodate")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam){
        shortLinkService.updateShortLink(requestParam);
        return Results.success();
    }
    /**
     * 分页查询短链接
     */
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }

    /**
     * 查询短链接分组内数量
     */
    @GetMapping("/api/short-link/v1/count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParam) {
        return Results.success(shortLinkService.listGroupShortLinkCount(requestParam));
    }
}
