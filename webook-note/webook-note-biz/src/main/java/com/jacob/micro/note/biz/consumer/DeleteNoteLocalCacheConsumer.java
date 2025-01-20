package com.jacob.micro.note.biz.consumer;

import com.jacob.micro.note.biz.constant.MQConstants;
import com.jacob.micro.note.biz.service.NoteService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Jacob
 * @Description: 删除本地笔记缓存
 * @Date: 2025/1/19 17:36
 * @Version: 1.0
 */
@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "webook_group", // Group
        topic = MQConstants.TOPIC_DELETE_NOTE_LOCAL_CACHE, // 消费的主题 Topic
        messageModel = MessageModel.BROADCASTING) // 广播模式
public class DeleteNoteLocalCacheConsumer implements RocketMQListener<String>  {

    @Resource
    private NoteService noteService;

    @Override
    public void onMessage(String body) {
        Long noteId = Long.valueOf(body);
        log.info("## 消费者消费成功, noteId: {}", noteId);

        noteService.deleteNoteLocalCache(noteId);
    }

}