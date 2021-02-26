package com.wllfengshu.mysql.model.entity;

import java.util.List;

/**
 * 响应结果
 */
public class ResultSet {

    /**
     * 操作结果
     */
    private boolean success;

    /**
     * 失败原因
     */
    private String failReasons;

    /**
     * 内容
     */
    private List<Object> content;
}
