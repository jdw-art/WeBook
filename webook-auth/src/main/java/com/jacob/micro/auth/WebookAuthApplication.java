package com.jacob.micro.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jacob.micro.auth.domain.mapper")
public class WebookAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebookAuthApplication.class, args);
    }

}
