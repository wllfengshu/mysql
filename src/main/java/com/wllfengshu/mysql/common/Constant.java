package com.wllfengshu.mysql.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 公共常量集合
 *
 * @author wangll
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    /**
     * 数据存储的路径
     */
    public static final String DATA_PATH = "/data";

    /**
     * 表结构文件
     */
    public static final String DATA_FILE_FRM = ".frm";

    /**
     * 表数据文件
     */
    public static final String DATA_FILE_IBD = ".ibd";
}