package com.jacob.micro.note.biz.service;

import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.note.biz.model.vo.FindNoteDetailReqVO;
import com.jacob.micro.note.biz.model.vo.FindNoteDetailRspVO;
import com.jacob.micro.note.biz.model.vo.PublishNoteReqVO;

/**
 * @Author: Jacob
 * @Description: 笔记业务
 * @Date: 2025/1/15 19:49
 * @Version: 1.0
 */
public interface NoteService {

    /**
     * 笔记发布
     * @param publishNoteReqVO
     * @return
     */
    Response<?> publishNote(PublishNoteReqVO publishNoteReqVO);

    /**
     * 笔记详情
     * @param findNoteDetailReqVO
     * @return
     */
    Response<FindNoteDetailRspVO> findNoteDetail(FindNoteDetailReqVO findNoteDetailReqVO);
}
