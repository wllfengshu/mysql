package com.wllfengshu.mysql.utils;

import com.wllfengshu.mysql.model.enumerate.StorageType;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 */
public class StringUtils {

    /**
     * 根据sql脚本获取表名
     *
     * @param sql
     * @return
     */
    public static List<String> giveTableNames(String sql) {
        List<String> tableNames = new ArrayList<>();

        return tableNames;
    }

    /**
     * 根据sql脚本获取字段（仅select的sql）
     *
     * @param sql
     * @return
     */
    public static List<String> giveFieldsBySql(String sql) {
        List<String> fields = new ArrayList<>();

        return fields;
    }

    /**
     * 根据表名获取存储引擎名
     *
     * @param tableName
     * @return
     */
    public static StorageType giveStorageEngineNameByTableName(String tableName) {

        return StorageType.Innodb;
    }
}
