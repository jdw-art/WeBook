package com.jacob.micro.auth.filter;

import com.jacob.micro.framework.common.constant.GlobalConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Author: Jacob
 * @Description: 请求头过滤器类，将用户 ID 放入上下文
 * @Date: 2024/6/26 20:09
 * @Version: 1.0
 */
@Component
@Slf4j
public class HeaderUserId2ContextFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从请求头中获取用户 ID
        String userId = request.getHeader(GlobalConstants.USER_ID);

        log.info("## HeaderUserId2ContextFilter, 用户 ID ：{}", userId);

        // 判断请求头中是否存在用户 ID
        if (StringUtils.isBlank(userId)) {
            // 若为空，则直接放行
            filterChain.doFilter(request, response);
            return;
        }

        // 如果 header 中存在 userId ，则设置到 ThreadLocal 中
        log.info("===== 设置 userId 到 ThreadLocal 中，用户 ID :{}", userId);
        LoginUserContextHolder.setUserId(userId);

        try {
            filterChain.doFilter(request, response);
        } finally {
            // 一定要删除 ThreadLocal， 防止内存泄漏
            LoginUserContextHolder.remove();
            log.info("=====删除 ThreadLocal，userId：{}", userId);
        }
    }
}
