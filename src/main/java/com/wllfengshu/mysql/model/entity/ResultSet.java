package com.wllfengshu.mysql.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 响应结果
 */
@Getter
@Setter
public class ResultSet {

    public ResultSet(boolean success) {
        this.success = success;
    }

    public ResultSet(boolean success, String failReasons) {
        this.success = success;
        this.failReasons = failReasons;
    }

    /**
     * 操作结果
     */
    private Boolean success;

    /**
     * 失败原因
     */
    private String failReasons;

    /**
     * 内容
     */
    private List<Object> content;
}
