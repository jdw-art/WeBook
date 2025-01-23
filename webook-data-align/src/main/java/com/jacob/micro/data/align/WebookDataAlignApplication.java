package com.jacob.micro.data.align;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Jacob
 * @Description: TODO
 * @Date: 2025/1/23 11:38
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.jacob.micro.data.align.domain.mapper")
public class WebookDataAlignApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebookDataAlignApplication.class, args);
    }
}
