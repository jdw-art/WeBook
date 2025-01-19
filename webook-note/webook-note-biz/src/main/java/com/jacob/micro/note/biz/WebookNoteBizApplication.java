package com.jacob.micro.note.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Jacob
 * @Description: TODO
 * @Date: 2025/1/15 17:42
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.jacob.micro.note.biz.domain.mapper")
@EnableFeignClients(basePackages = "com.jacob.micro")
public class WebookNoteBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebookNoteBizApplication.class, args);
    }
}
