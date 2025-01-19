package com.jacob.micro.note.biz.service;

import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.note.biz.model.vo.*;

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

    /**
     * 笔记更新
     * @param updateNoteReqVO
     * @return
     */
    Response<?> updateNote(UpdateNoteReqVO updateNoteReqVO);

    /**
     * 删除笔记本地缓存
     * @param noteId
     */
    void deleteNoteLocalCache(Long noteId);

    /**
     * 删除笔记
     * @param deleteNoteReqVO
     * @return
     */
    Response<?> deleteNote(DeleteNoteReqVO deleteNoteReqVO);

    /**
     * 笔记仅对自己可见
     * @param updateNoteVisibleOnlyMeReqVO
     * @return
     */
    Response<?> visibleOnlyMe(UpdateNoteVisibleOnlyMeReqVO updateNoteVisibleOnlyMeReqVO);
}
