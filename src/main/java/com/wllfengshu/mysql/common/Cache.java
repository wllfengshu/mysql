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
     *
     */
    public static final Map<String, String> MAP = new HashMap<>();
}
