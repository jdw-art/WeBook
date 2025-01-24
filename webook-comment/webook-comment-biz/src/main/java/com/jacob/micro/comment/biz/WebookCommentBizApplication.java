package com.jacob.micro.comment.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @Author: Jacob
 * @Description: TODO
 * @Date: 2025/1/23 16:53
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.jacob.micro.comment.biz.domain.mapper")
@EnableRetry    // 启用 Spring Retry
public class WebookCommentBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebookCommentBizApplication.class, args);
    }
}
