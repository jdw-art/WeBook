package com.jacob.micro.kv.biz.service;

import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.kv.dto.req.AddNoteContentReqDTO;
import com.jacob.micro.kv.dto.req.DeleteNoteContentReqDTO;
import com.jacob.micro.kv.dto.req.FindNoteContentReqDTO;
import com.jacob.micro.kv.dto.rsp.FindNoteContentRspDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: Jacob
 * @Description: 笔记内容存储业务
 * @Date: 2025/1/15 14:05
 * @Version: 1.0
 */
public interface NoteContentService {

    /**
     * 添加笔记内容
     * @param addNoteContentReqDTO
     * @return
     */
    Response<?> addNoteContent(AddNoteContentReqDTO addNoteContentReqDTO);

    /**
     * 查询笔记内容
     * @param findNoteContentReqDTO
     * @return
     */
    Response<FindNoteContentRspDTO> findNoteContent(FindNoteContentReqDTO findNoteContentReqDTO);

    /**
     * 删除笔记内容
     *
     * @param deleteNoteContentReqDTO
     * @return
     */
    Response<?> deleteNoteContent(DeleteNoteContentReqDTO deleteNoteContentReqDTO);
}
