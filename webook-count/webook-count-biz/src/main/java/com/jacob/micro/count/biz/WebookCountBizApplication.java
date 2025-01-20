package com.jacob.micro.count.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Jacob
 * @Description: TODO
 * @Date: 2025/1/20 15:11
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.jacob.micro.count.biz.domain.mapper")
public class WebookCountBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebookCountBizApplication.class, args);
    }
}
