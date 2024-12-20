package com.lzq.shortlink.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author lzq
 * @Description
 * @DATE 2024/12/20下午2:00
 */
@SpringBootApplication
@MapperScan("com.lzq.shortlink.project.dao.mapper")
public class ShortLinkApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortLinkApplication.class);
    }
}
