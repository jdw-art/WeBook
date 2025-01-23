package com.jacob.micro.data.align.domain.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @Author: Jacob
 * @Description: 更新
 * @Date: 2025/1/23 16:31
 * @Version: 1.0
 */
public interface UpdateRecordMapper {

    /**
     * 更新 t_user_count 计数表总关注数
     * @param userId
     * @return
     */
    int updateUserFollowingTotalByUserId(@Param("userId") long userId,
                                         @Param("followingTotal") int followingTotal);
}
