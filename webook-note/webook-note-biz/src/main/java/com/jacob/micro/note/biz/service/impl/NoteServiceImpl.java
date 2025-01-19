package com.jacob.micro.note.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.base.Preconditions;
import com.jacob.micro.framework.biz.context.holder.LoginUserContextHolder;
import com.jacob.micro.framework.common.exception.BizException;
import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.note.biz.domain.dataobject.NoteDO;
import com.jacob.micro.note.biz.domain.mapper.NoteDOMapper;
import com.jacob.micro.note.biz.domain.mapper.TopicDOMapper;
import com.jacob.micro.note.biz.enums.NoteStatusEnum;
import com.jacob.micro.note.biz.enums.NoteTypeEnum;
import com.jacob.micro.note.biz.enums.NoteVisibleEnum;
import com.jacob.micro.note.biz.enums.ResponseCodeEnum;
import com.jacob.micro.note.biz.model.vo.PublishNoteReqVO;
import com.jacob.micro.note.biz.rpc.DistributedIdGeneratorRpcService;
import com.jacob.micro.note.biz.rpc.KeyValueRpcService;
import com.jacob.micro.note.biz.service.NoteService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author: Jacob
 * @Description: 笔记业务
 * @Date: 2025/1/15 19:50
 * @Version: 1.0
 */
@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

    @Resource
    private NoteDOMapper noteDOMapper;

    @Resource
    private TopicDOMapper topicDOMapper;

    @Resource
    private DistributedIdGeneratorRpcService distributedIdGeneratorRpcService;

    @Resource
    private KeyValueRpcService keyValueRpcService;

    @Override
    public Response<?> publishNote(PublishNoteReqVO publishNoteReqVO) {
        Integer type = publishNoteReqVO.getType();
        NoteTypeEnum noteTypeEnum = NoteTypeEnum.valueOf(type);

        if (Objects.isNull(noteTypeEnum)) {
            throw new BizException(ResponseCodeEnum.NOTE_TYPE_ERROR);
        }

        String imgUris = null;
        // 笔记内容是否为空，默认值为 true，即空
        Boolean isContentEmpty = true;
        String videoUri = null;
        switch (noteTypeEnum) {
            case IMAGE_TEXT:
                List<String> imgUriList = publishNoteReqVO.getImgUris();
                // 校验图像是否为空
                Preconditions.checkArgument(CollUtil.isNotEmpty(imgUriList));
                // 校验图片数量
                Preconditions.checkArgument(imgUriList.size() <= 8, "笔记图片不能多于8张");
                imgUris = StringUtils.join(imgUriList, ",");

                break;
            case VIDEO:
                videoUri = publishNoteReqVO.getVideoUri();
                // 校验视频链接是否为空
                Preconditions.checkArgument(StringUtils.isNotBlank(videoUri), "笔记视频不能为空");
                break;
            default:
                break;
        }
        // 调用分布式ID生成 RPC 服务，生成笔记 ID
        String snowflakeId = distributedIdGeneratorRpcService.getSnowflakeId();
        // 笔记内容 UUID
        String contentUuid = null;

        // 笔记内容
        String content = publishNoteReqVO.getContent();

        // 若用户填写了笔记内容
        if (StringUtils.isNotBlank(content)) {
            // 内容是否为空，置为 false， 即不为空
            isContentEmpty = false;
            // 生成笔记UUID
            contentUuid = UUID.randomUUID().toString();
            // RPC：调用 KV 键值对存储服务，存储短文本
            boolean isSaveSuccess = keyValueRpcService.saveNoteContent(contentUuid, content);

            // 若存储失败，抛出业务异常，提示用户发布笔记失败
            if (!isSaveSuccess) {
                throw new BizException(ResponseCodeEnum.NOTE_PUBLISH_FAIL);
            }
        }

        // 话题
        Long topicId = publishNoteReqVO.getTopicId();
        String topicName = null;
        if (Objects.nonNull(topicId)) {
            // 获取话题名称
            topicName = topicDOMapper.selectNameByPrimaryKey(topicId);
        }

        // 发布者用户 ID
        Long creatorId = LoginUserContextHolder.getUserId();

        // 构建笔记 DO 对象
        NoteDO noteDO = NoteDO.builder()
                .id(Long.valueOf(snowflakeId))
                .isContentEmpty(isContentEmpty)
                .creatorId(creatorId)
                .imgUris(imgUris)
                .title(publishNoteReqVO.getTitle())
                .topicId(publishNoteReqVO.getTopicId())
                .topicName(topicName)
                .type(type)
                .visible(NoteVisibleEnum.PUBLIC.getCode())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .status(NoteStatusEnum.NORMAL.getCode())
                .isTop(Boolean.FALSE)
                .videoUri(videoUri)
                .contentUuid(contentUuid)
                .build();
        try {
            // 笔记入库存储
            noteDOMapper.insert(noteDO);
        } catch (Exception e) {
            log.error("===> 笔记存储失败");

            // RPC：笔记保存失败，则删除笔记内容
            if (StringUtils.isNotBlank(contentUuid)) {
                keyValueRpcService.deleteNoteContent(contentUuid);
            }
        }
        return Response.success();
    }
}
