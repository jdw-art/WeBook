package com.jacob.micro.oss.biz.controller;

import com.jacob.micro.framework.biz.operationlog.aspect.ApiOperationLog;
import com.jacob.micro.framework.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jacob
 * @Description: Feign 测试接口
 * @Date: 2024-07-14 16:20
 * @Version: 1.0
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class TestFenginController {

    @PostMapping("/test")
    @ApiOperationLog(description = "Feign 测试接口")
    public Response<?> test() {
        return Response.success();
    }
}
