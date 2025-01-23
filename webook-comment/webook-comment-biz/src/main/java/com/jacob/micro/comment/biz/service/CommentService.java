package com.jacob.micro.comment.biz.service;

import com.jacob.micro.comment.biz.model.vo.PublishCommentReqVO;
import com.jacob.micro.framework.common.response.Response;

/**
 * @Author: Jacob
 * @Description: 评论业务
 * @Date: 2025/1/23 17:09
 * @Version: 1.0
 */
public interface CommentService {

    /**
     * 发布评论
     * @param publishCommentReqVO
     * @return
     */
    Response<?> publishComment(PublishCommentReqVO publishCommentReqVO);
}
