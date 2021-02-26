package com.wllfengshu.mysql.model.vo;

import lombok.Data;

/**
 * 用户发送过来的sql对象
 */
@Data
public class SqlVO {

    /**
     * sql脚本
     */
    private String sql;
}
