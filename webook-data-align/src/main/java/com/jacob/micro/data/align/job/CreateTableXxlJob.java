package com.jacob.micro.data.align.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @Author: Jacob
 * @Description: 定时任务：自动创建日增量计数变更表
 * @Date: 2025/1/23 11:44
 * @Version: 1.0
 */
@Component
public class CreateTableXxlJob {

    @XxlJob("createTableJobHandler")
    public void createTableJobHandler() throws Exception {
        XxlJobHelper.log("## 开始初始化明日增量数据表...");
    }
}
