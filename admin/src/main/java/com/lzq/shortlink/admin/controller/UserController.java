package com.lzq.shortlink.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lzq
 * @Description
 * @DATE 2024/12/17下午2:00
 */
@RestController
public class UserController {
    /**
     *根据用户名查询用户信息
     */
    @GetMapping("/api/shortlink/v1/user/{username}")
    public String getUserByUsername(@PathVariable("username") String username){
        return "Hi " + username;
    }
}
