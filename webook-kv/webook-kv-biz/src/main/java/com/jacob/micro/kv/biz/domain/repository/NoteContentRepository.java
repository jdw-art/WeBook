package com.jacob.micro.kv.biz.domain.repository;

import com.jacob.micro.kv.biz.domain.dataobject.NoteContentDO;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

/**
 * @Author: Jacob
 * @Description: Cassandra 数据库操作接口
 * @Date: 2025/1/15 13:38
 * @Version: 1.0
 */
public interface NoteContentRepository extends CassandraRepository<NoteContentDO, UUID> {
}
