package com.jacob.micro.count.biz.config;

import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: Jacob
 * @Description: TODO
 * @Date: 2025/1/20 15:16
 * @Version: 1.0
 */
@Configuration
@Import(RocketMQAutoConfiguration.class)
public class RocketMQConfig {
}

