package com.jacob.micro.comment.biz.retry;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: Jacob
 * @Description: 发送 MQ 重试工具类
 * @Date: 2025/1/23 17:01
 * @Version: 1.0
 */
@Component
@Slf4j
public class SendMqRetryHelper {

    @Resource
    private RocketMQTemplate rocketMQTemplate;


}
