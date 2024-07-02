package com.jacob.micro.oss.biz.service.impl;

import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.oss.biz.service.FileService;
import com.jacob.micro.oss.biz.strategy.FileStrategy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Jacob
 * @Description: 文件业务实现类
 * @Date: 2024/7/2 20:16
 * @Version: 1.0
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Resource
    private FileStrategy fileStrategy;

    @Override
    public Response<?> uploadFile(MultipartFile file) {
        // 上传文件到
        fileStrategy.uploadFile(file, "webook");

        return Response.success();
    }
}
