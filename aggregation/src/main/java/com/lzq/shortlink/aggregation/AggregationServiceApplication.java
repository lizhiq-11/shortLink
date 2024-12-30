
package com.lzq.shortlink.aggregation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 短链接聚合应用
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "com.lzq.shortlink.admin",
        "com.lzq.shortlink.project",
        "com.lzq.shortlink.aggregation"
})
@MapperScan(value = {
        "com.lzq.shortlink.project.dao.mapper",
        "com.lzq.shortlink.admin.dao.mapper"
})
public class AggregationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregationServiceApplication.class, args);
    }
}