package com.wllfengshu.mysql.model.dto;

import lombok.Data;

/**
 * 优化器生成的执行计划
 */
@Data
public class ExecutePlanDTO extends AnalyseSqlDTO{

    /**
     * 索引列
     */
    private String indexColumn;
}
