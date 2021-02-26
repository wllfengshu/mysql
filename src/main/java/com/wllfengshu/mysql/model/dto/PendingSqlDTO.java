package com.wllfengshu.mysql.model.dto;

import com.wllfengshu.mysql.model.enumerate.SqlType;
import com.wllfengshu.mysql.model.vo.SqlVO;
import lombok.Data;

import java.util.List;

/**
 * 连接器处理后的待执行的sql对象
 */
@Data
public class PendingSqlDTO extends SqlVO {

    /**
     * 数据库名
     */
    private String dbName;

    /**
     * sql类型
     */
    private SqlType sqlType;

    /**
     * sql涉及到的表名的集合
     */
    private List<String> tableNames;
}
