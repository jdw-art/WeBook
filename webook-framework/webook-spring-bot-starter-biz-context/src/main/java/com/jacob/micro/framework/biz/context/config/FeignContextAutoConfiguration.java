package com.jacob.micro.framework.biz.context.config;

import com.jacob.micro.framework.biz.context.interceptor.FeignRequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Author: Jacob
 * @Description: Feign 自动化配置类
 * @Date: 2024-07-15 20:15
 * @Version: 1.0
 */
@AutoConfiguration
public class FeignContextAutoConfiguration {

    @Bean
    public FeignRequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
