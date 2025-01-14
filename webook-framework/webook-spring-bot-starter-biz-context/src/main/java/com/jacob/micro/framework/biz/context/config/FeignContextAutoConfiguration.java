package com.jacob.micro.framework.biz.context.config;

import com.jacob.micro.framework.biz.context.interceptor.FeignRequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Author: Jacob
 * @Description: Feign 请求拦截器自动配置
 * @Date: 2025/1/14 13:09
 * @Version: 1.0
 */
@AutoConfiguration
public class FeignContextAutoConfiguration {
    @Bean
    public FeignRequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
