package com.jacob.micro.user.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Jacob
 * @Description: 用户服务启动类
 * @Date: 2024/7/7 16:26
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.jacob.micro.user.biz.domain.mapper")
public class WebookUserBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebookUserBizApplication.class, args);
    }
}
