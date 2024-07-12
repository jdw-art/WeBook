package com.jacob.micro.user.biz.service;

import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.user.biz.model.vo.UpdateUserInfoReqVO;

/**
 * @Author: Jacob
 * @Description: 更新用户接口
 * @Date: 2024/7/12 22:12
 * @Version: 1.0
 */
public interface UserService {

    /**
     * 更新用户信息
     * @param updateUserInfoReqVO
     * @return
     */
    Response<?> updateUserInfo(UpdateUserInfoReqVO updateUserInfoReqVO);
}
