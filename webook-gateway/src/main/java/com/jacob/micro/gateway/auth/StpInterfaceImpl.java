package com.jacob.micro.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.jacob.micro.gateway.constant.RedisKeyConstants;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @Author: Jacob
 * @Description: SaToken 自定义权限验证接口扩展类
 * @Date: 2024/6/25 14:17
 * @Version: 1.0
 */
@Component
@Slf4j
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private ObjectMapper objectMapper;

    /**
     * 获取用户权限
     *
     * @param loginId
     * @param loginType
     * @return
     */
    @SneakyThrows
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        log.info("## 获取用户权限列表, loginId: {}", loginId);

        // 构建 用户-角色 Redis key
        String userRolesKey = RedisKeyConstants.buildUserRoleKey(Long.valueOf(loginId.toString()));

        // 根据用户ID 从Redis中获取该用户的角色集合
        String userRolesValue = redisTemplate.opsForValue().get(userRolesKey);

        if (StringUtils.isBlank(userRolesValue)) {
            return null;
        }

        // 将 JSON 字符串转换为 List<String> 集合
        List<String> userRoleKeys = objectMapper.readValue(userRolesValue, new TypeReference<>() {});

        if (CollUtil.isNotEmpty(userRoleKeys)) {
            // 查询这些角色对应的权限
            // 构建 角色-权限 Redis Key 集合
            List<String> rolePermissionKeys = userRoleKeys.stream()
                    .map(RedisKeyConstants::buildRolePermissionsKey)
                    .toList();

            // 通过 Key 集合批量查询权限，提升查询性能
            List<String> rolePermissionsValue = redisTemplate.opsForValue().multiGet(rolePermissionKeys);

            if (CollUtil.isNotEmpty(rolePermissionsValue)) {
                List<String> permissions = Lists.newArrayList();

                // 遍历所有角色的权限集合，统一添加到 permissions 集合中
                rolePermissionsValue.forEach(jsonValue -> {
                    try {
                        // 将Json字符串转换为 List<String> 权限集合
                        List<String> rolePermissions = objectMapper.readValue(jsonValue, new TypeReference<>() {});
                        permissions.addAll(rolePermissions);
                    } catch (JsonProcessingException e) {
                        log.error("==> Json 解析错误：", e);
                    }
                });

                // 返回此用户所拥有的权限
                return permissions;
            }

        }

        return null;
    }

    /**
     * 获取用户角色
     *
     * @param loginId
     * @param loginType
     * @return
     */
    @SneakyThrows
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        log.info("## 获取用户角色列表, loginId: {}", loginId);

        // 构建 用户-角色 Redis KEY
        String userRolesKey = RedisKeyConstants.buildUserRoleKey(Long.valueOf(loginId.toString()));

        // 根据用户 ID，从 Redis 中获取该用户的角色集合
        String userRolesValue = redisTemplate.opsForValue().get(userRolesKey);

        if (StringUtils.isBlank(userRolesValue)) {
            return null;
        }

        // 将 JSON 字符串转换为 List<String>
        return objectMapper.readValue(userRolesValue, new TypeReference<>() {});
    }
}
