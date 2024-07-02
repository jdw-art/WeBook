package com.jacob.micro.oss.biz.service;

import com.jacob.micro.framework.common.response.Response;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Jacob
 * @Description: 文件业务接口
 * @Date: 2024/7/2 20:15
 * @Version: 1.0
 */
public interface FileService {

    /**
     * 文件上传
     * @param file
     * @return
     */
    Response<?> uploadFile(MultipartFile file);
}
