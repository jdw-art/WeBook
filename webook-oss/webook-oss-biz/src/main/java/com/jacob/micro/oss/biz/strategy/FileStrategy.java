package com.jacob.micro.oss.biz.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Jacob
 * @Description: 文件策略接口
 * @Date: 2024/7/2 20:08
 * @Version: 1.0
 */
public interface FileStrategy {

    /**
     * 文件上传
     * @param file
     * @param bucketName
     * @return
     */
    String uploadFile(MultipartFile file, String bucketName);

}
