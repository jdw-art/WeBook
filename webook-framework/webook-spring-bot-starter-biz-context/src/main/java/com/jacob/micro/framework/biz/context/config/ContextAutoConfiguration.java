package com.jacob.micro.framework.biz.context.config;

import com.jacob.micro.framework.biz.context.filter.HeaderUserId2ContextFilter;
import jakarta.servlet.FilterRegistration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @Author: Jacob
 * @Description: 线程上下问变量自动配置类
 * @Date: 2024/6/30 15:56
 * @Version: 1.0
 */
@AutoConfiguration
public class ContextAutoConfiguration {

    @Bean
    public FilterRegistrationBean<HeaderUserId2ContextFilter> filterFilterRegistrationBean() {
        HeaderUserId2ContextFilter filter = new HeaderUserId2ContextFilter();
        FilterRegistrationBean<HeaderUserId2ContextFilter> bean = new FilterRegistrationBean<>(filter);
        return bean;
    }
}
