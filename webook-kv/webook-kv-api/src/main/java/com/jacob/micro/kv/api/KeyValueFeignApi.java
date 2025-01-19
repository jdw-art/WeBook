package com.jacob.micro.kv.api;

import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.kv.constant.ApiConstants;
import com.jacob.micro.kv.dto.req.AddNoteContentReqDTO;
import com.jacob.micro.kv.dto.req.DeleteNoteContentReqDTO;
import com.jacob.micro.kv.dto.req.FindNoteContentReqDTO;
import com.jacob.micro.kv.dto.rsp.FindNoteContentRspDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: Jacob
 * @Description: K-V 键值存储 Feign 接口
 * @Date: 2025/1/15 14:18
 * @Version: 1.0
 */
@FeignClient(name = ApiConstants.SERVICE_NAME)
public interface KeyValueFeignApi {

    String PREFIX = "/kv";

    @PostMapping(value = PREFIX + "/note/content/add")
    Response<?> addNoteContent(@RequestBody AddNoteContentReqDTO addNoteContentReqDTO);

    @PostMapping(value = PREFIX + "/note/content/find")
    Response<FindNoteContentRspDTO> findNoteContent(@RequestBody FindNoteContentReqDTO findNoteContentReqDTO);

    @PostMapping(value = PREFIX + "/note/content/delete")
    Response<?> deleteNoteContent(@RequestBody DeleteNoteContentReqDTO deleteNoteContentReqDTO);
}
