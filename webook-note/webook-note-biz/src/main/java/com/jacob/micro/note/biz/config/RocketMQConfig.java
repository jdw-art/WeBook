package com.jacob.micro.note.biz.config;

import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: Jacob
 * @Description: RocketMQ 配置
 * @Date: 2025/1/19 17:31
 * @Version: 1.0
 */
@Configuration
@Import(RocketMQAutoConfiguration.class)
public class RocketMQConfig {
}
