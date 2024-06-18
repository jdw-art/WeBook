package com.jacob.micro.auth.service;

import com.jacob.micro.auth.model.vo.user.UserLoginRepVO;
import com.jacob.micro.framework.common.response.Response;

/**
 * @Author: Jacob
 * @Description: 用户服务接口
 * @Date: 2024/6/17 14:41
 * @Version: 1.0
 */
public interface UserService {

    /**
     * 用户登录与注册
     * @param userLoginRepVO
     * @return
     */
    Response<String> loginAndRegister(UserLoginRepVO userLoginRepVO);
}
