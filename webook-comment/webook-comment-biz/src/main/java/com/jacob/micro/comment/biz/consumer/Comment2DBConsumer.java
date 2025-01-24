package com.jacob.micro.comment.biz.consumer;

import com.alibaba.nacos.shaded.com.google.common.util.concurrent.RateLimiter;
import com.jacob.micro.comment.biz.constant.MQConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Jacob
 * @Description: 评论批量写库
 * @Date: 2025/1/24 10:12
 * @Version: 1.0
 */
@Component
@Slf4j
public class Comment2DBConsumer {

    @Value("${rocketmq.name-server}")
    private String namesrvAddr;

    private DefaultMQPushConsumer consumer;

    // 每秒创建 1000 个令牌
    private RateLimiter rateLimiter = RateLimiter.create(1000);

    @Bean
    private DefaultMQPushConsumer mqPushConsumer() throws MQClientException {
        // Group 组
        String group = "webook_group" + MQConstants.TOPIC_PUBLISH_COMMENT;

        // 创建一个新的 DefaultMQPushConsumer 实例，并指定消费者的消费组名
        consumer = new DefaultMQPushConsumer(group);

        // 设置 NameServer 地址
        consumer.setNamesrvAddr(namesrvAddr);

        // 订阅指定的主题，并设置主题的订阅规则（“*” 表示订阅所有标签的主题）
        consumer.subscribe(MQConstants.TOPIC_PUBLISH_COMMENT, "*");

        // 设置消费者消费消息的起始位置，如果队列中没有消息，则从最新的消息开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        // 设置消息消费模式，这里使用集群模式（CLUSTERING）
        consumer.setMessageModel(MessageModel.CLUSTERING);

        // 设置每批次消费的最大消息数量，这里设置30，表示每次拉取时最多消费30条消息
        consumer.setConsumeMessageBatchMaxSize(30);

        // 注册消息监听器
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            log.info("==> 本批次消息大小：{}", msgs.size());
            try {
                // 令牌桶限流
                rateLimiter.acquire();

                for (MessageExt msg : msgs) {
                    String message = new String(msg.getBody());
                    log.info("==> Consumer - Received message: {}", message);

                    // TODO：业务处理
                }

                // 手动 ACK, 告诉 RocketMQ 消息已经处理完成
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                log.error("", e);
                // 手动 ACK，告诉 RocketMQ 消息处理失败，重新放入队列
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        // 启动消费者
        consumer.start();
        return consumer;
    }

    public void destroy() {
        if (Objects.nonNull(consumer)) {
            try {
                consumer.shutdown();    // 关闭消费者
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }
}
