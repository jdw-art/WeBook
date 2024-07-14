package com.jacob.micro.oss.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @Author: Jacob
 * @Description: Feign 表单配置类
 * @Date: 2024-07-14 16:57
 * @Version: 1.0
 */
@Configuration
public class FeignFormConfig {

    @Bean
    public Encoder feignFormConfig() {
        return new SpringFormEncoder();
    }
}
