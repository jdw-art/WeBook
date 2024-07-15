package com.jacob.micro.framework.biz.context.interceptor;

import com.jacob.micro.framework.biz.context.holder.LoginUserContextHolder;
import com.jacob.micro.framework.common.constant.GlobalConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @Author: Jacob
 * @Description: Feign 请求拦截器
 * @Date: 2024-07-15 20:12
 * @Version: 1.0
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 获取当前上下文的用户 ID
        Long userId = LoginUserContextHolder.getUserId();

        // 若不为空，则添加到请求头中
        if (Objects.nonNull(userId)) {
            requestTemplate.header(GlobalConstants.USER_ID, String.valueOf(userId));
            log.info("########## feign 请求设置请求头 userId: {}", userId);
        }
    }
}
