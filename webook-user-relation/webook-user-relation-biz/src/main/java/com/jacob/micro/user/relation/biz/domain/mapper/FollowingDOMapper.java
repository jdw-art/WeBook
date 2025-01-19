package com.jacob.micro.user.relation.biz.domain.mapper;

import com.jacob.micro.user.relation.biz.domain.dataobject.FollowingDO;

public interface FollowingDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FollowingDO record);

    int insertSelective(FollowingDO record);

    FollowingDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FollowingDO record);

    int updateByPrimaryKey(FollowingDO record);
}