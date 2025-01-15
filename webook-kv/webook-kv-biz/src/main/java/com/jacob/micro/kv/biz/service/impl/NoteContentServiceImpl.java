package com.jacob.micro.kv.biz.service.impl;

import com.jacob.micro.framework.common.exception.BizException;
import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.kv.biz.domain.dataobject.NoteContentDO;
import com.jacob.micro.kv.biz.domain.repository.NoteContentRepository;
import com.jacob.micro.kv.biz.enums.ResponseCodeEnum;
import com.jacob.micro.kv.biz.service.NoteContentService;
import com.jacob.micro.kv.dto.req.AddNoteContentReqDTO;
import com.jacob.micro.kv.dto.req.DeleteNoteContentReqDTO;
import com.jacob.micro.kv.dto.req.FindNoteContentReqDTO;
import com.jacob.micro.kv.dto.rsp.FindNoteContentRspDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * @Author: Jacob
 * @Description: Key-Value 业务
 * @Date: 2025/1/15 14:06
 * @Version: 1.0
 */
@Service
@Slf4j
public class NoteContentServiceImpl implements NoteContentService {

    @Resource
    private NoteContentRepository noteContentRepository;

    @Override
    public Response<?> addNoteContent(AddNoteContentReqDTO addNoteContentReqDTO) {
        // 笔记ID
        Long noteId = addNoteContentReqDTO.getNoteId();
        // 笔记内容
        String content = addNoteContentReqDTO.getContent();

        // 构造笔记DO
        NoteContentDO noteContentDO = NoteContentDO.builder()
                .id(UUID.randomUUID())
                .content(content)
                .build();

        // 输入数据
        noteContentRepository.save(noteContentDO);

        return Response.success();
    }

    @Override
    public Response<FindNoteContentRspDTO> findNoteContent(FindNoteContentReqDTO findNoteContentReqDTO) {
        String noteId = findNoteContentReqDTO.getNoteId();
        Optional<NoteContentDO> optional = noteContentRepository.findById(UUID.fromString(noteId));

        // 如果笔记内容不存在
        if (!optional.isPresent()) {
            throw new BizException(ResponseCodeEnum.NOTE_CONTENT_NOT_FOUND);
        }

        NoteContentDO noteContentDO = optional.get();
        // 构建返回参数
        FindNoteContentRspDTO findNoteContentRspDTO = FindNoteContentRspDTO.builder()
                .noteId(noteContentDO.getId())
                .content(noteContentDO.getContent())
                .build();
        return Response.success(findNoteContentRspDTO);
    }

    @Override
    public Response<?> deleteNoteContent(DeleteNoteContentReqDTO deleteNoteContentReqDTO) {
        // 笔记 ID
        String noteId = deleteNoteContentReqDTO.getNoteId();
        // 删除笔记内容
        noteContentRepository.deleteById(UUID.fromString(noteId));

        return Response.success();
    }
}


