package com.lzq.shortlink.admin.controller;

import com.lzq.shortlink.admin.common.convention.result.Result;
import com.lzq.shortlink.admin.common.convention.result.Results;
import com.lzq.shortlink.admin.dto.rep.ShortLinkGroupSaveReqDTO;
import com.lzq.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短链接分组控制层
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * 新增短链接分组
     */
    @PostMapping("/api/short-link/admin/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }
}
