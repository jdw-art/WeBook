package com.jacob.micro.user.relation.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Jacob
 * @Description: TODO
 * @Date: 2025/1/19 19:51
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.jacob.micro.user.relation.biz.domain.mapper")
public class WebookUserRelationBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebookUserRelationBizApplication.class, args);
    }
}
