package com.jacob.micro.kv.biz;

import com.jacob.micro.framework.common.util.JsonUtils;
import com.jacob.micro.kv.biz.domain.dataobject.NoteContentDO;
import com.jacob.micro.kv.biz.domain.repository.NoteContentRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

/**
 * @Author: Jacob
 * @Description: Cassandra连接测试
 * @Date: 2025/1/15 13:44
 * @Version: 1.0
 */
@SpringBootTest
@Slf4j
public class CassandraTests {

    @Resource
    private NoteContentRepository noteContentRepository;

    @Test
    void testInsert() {
        NoteContentDO noteContentDO = NoteContentDO.builder()
                .id(UUID.randomUUID())
                .content("测试笔记内容")
                .build();

        noteContentRepository.save(noteContentDO);
    }

    @Test
    void testSelect() {
        Optional<NoteContentDO> optional = noteContentRepository.findById(UUID.fromString("cdffe45a-9600-4ec3-a996-77e2471c7cb4"));
        optional.ifPresent(noteContentDO -> log.info("查询结果: {}", JsonUtils.toJsonString(noteContentDO)));
    }

    @Test
    void testDelete() {
        noteContentRepository.deleteById(UUID.fromString("cdffe45a-9600-4ec3-a996-77e2471c7cb4"));
    }
}
