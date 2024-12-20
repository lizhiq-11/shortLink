package com.lzq.shortlink.project.controller;

import com.lzq.shortlink.project.common.convention.result.Result;
import com.lzq.shortlink.project.common.convention.result.Results;
import com.lzq.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.lzq.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.lzq.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
