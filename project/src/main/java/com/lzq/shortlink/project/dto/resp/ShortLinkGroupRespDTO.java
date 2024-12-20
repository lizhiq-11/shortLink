package com.lzq.shortlink.project.dto.resp;

import lombok.Data;

/**
 * 短链接分组信息响应对象
 */
@Data
public class ShortLinkGroupRespDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 短链接数量
     */
    private Integer shortLinkCount;
}
