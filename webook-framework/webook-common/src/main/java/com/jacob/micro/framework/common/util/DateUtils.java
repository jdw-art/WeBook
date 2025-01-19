package com.jacob.micro.framework.common.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @Author: Jacob
 * @Description: 日期工具类
 * @Date: 2025/1/19 20:39
 * @Version: 1.0
 */
public class DateUtils {

    /**
     * LocalDateTime 转时间戳
     *
     * @param localDateTime
     * @return
     */
    public static long localDateTime2Timestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}