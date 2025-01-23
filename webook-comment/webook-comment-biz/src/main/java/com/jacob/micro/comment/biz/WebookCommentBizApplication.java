package com.jacob.micro.comment.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Jacob
 * @Description: TODO
 * @Date: 2025/1/23 16:53
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.jacob.micro.comment.biz.domain.mapper")
public class WebookCommentBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebookCommentBizApplication.class, args);
    }
}
