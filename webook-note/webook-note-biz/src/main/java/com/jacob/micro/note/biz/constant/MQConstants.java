package com.jacob.micro.note.biz.constant;

/**
 * @Author: Jacob
 * @Description: TODO
 * @Date: 2025/1/19 17:32
 * @Version: 1.0
 */
public interface MQConstants {

    /**
     * Topic 主题：删除笔记本地缓存
     */
    String TOPIC_DELETE_NOTE_LOCAL_CACHE = "DeleteNoteLocalCacheTopic";

    /**
     * Topic: 点赞、取消点赞共用一个
     */
    String TOPIC_LIKE_OR_UNLIKE = "LikeUnlikeTopic";

    /**
     * 点赞标签
     */
    String TAG_LIKE = "Like";

    /**
     * Tag 标签：取消点赞
     */
    String TAG_UNLIKE = "Unlike";
}
