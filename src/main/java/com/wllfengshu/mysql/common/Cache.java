package com.wllfengshu.mysql.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Cache {

    /**
     * 表结构：<数据库名-表名, 表结构>
     */
    public static final Map<String, String> DB_TABLE_FRM_MAP = new HashMap<>();
}
