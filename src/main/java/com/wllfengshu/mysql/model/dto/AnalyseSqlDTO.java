package com.wllfengshu.mysql.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 分析器处理后的sql对象
 */
@Data
public class AnalyseSqlDTO extends PendingSqlDTO {

    /**
     * 字段的集合
     */
    private List<String> fields;

}
