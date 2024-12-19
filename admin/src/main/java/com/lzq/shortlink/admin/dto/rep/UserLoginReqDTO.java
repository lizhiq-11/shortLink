package com.lzq.shortlink.admin.dto.rep;

import lombok.Data;

/**
 * 用户登录请求入参
 */
@Data
public class UserLoginReqDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
