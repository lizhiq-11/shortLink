package com.lzq.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lzq.shortlink.admin.common.database.BaseDO;
import lombok.Data;

/**
 * @Author lzq
 * @Description
 * @DATE 2024/12/17下午2:58
 */
@Data
@TableName("t_user")
public class UserDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 注销时间戳
     */
    private Long deletionTime;
}
