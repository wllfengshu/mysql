package com.wllfengshu.mysql.utils;

import com.wllfengshu.mysql.common.Constant;
import com.wllfengshu.mysql.model.enumerate.StorageType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 */
public class StringUtils {

    /**
     * 根据使用哪个数据库的sql，返回选择的数据库名
     *
     * @param useDbSql
     * @return
     */
    public static String giveDbNameByUseDbSql(String useDbSql) {

        return null;
    }

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
     * @param dbName
     * @param tableName
     * @return
     */
    public static StorageType giveStorageEngineNameByTableName(String dbName, String tableName) {
        File frm = new File(Constant.DATA_PATH + '/' + dbName + '/' + tableName + ".frm");

        return StorageType.Innodb;
    }

}
