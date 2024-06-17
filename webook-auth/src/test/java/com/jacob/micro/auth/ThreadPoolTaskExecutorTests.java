package com.jacob.micro.auth;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Author: Jacob
 * @Description: 线程池单元测试类
 * @Date: 2024/6/16 20:17
 * @Version: 1.0
 */
@SpringBootTest
@Slf4j
public class ThreadPoolTaskExecutorTests {

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 测试线程池
     */
    @Test
    void testSubmit() {
        threadPoolTaskExecutor.submit(() -> log.info("异步线程中说: 犬小哈专栏"));
    }
}
