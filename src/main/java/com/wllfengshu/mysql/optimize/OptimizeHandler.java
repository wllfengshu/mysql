package com.wllfengshu.mysql.optimize;

import com.wllfengshu.mysql.model.dto.AnalyseSqlDTO;
import com.wllfengshu.mysql.model.dto.ExecutePlanDTO;
import com.wllfengshu.mysql.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class OptimizeHandler {

    /**
     * 生成执行计划，选择索引
     *
     * @param analyseSqlDTO
     * @return
     */
    public ExecutePlanDTO optimize(AnalyseSqlDTO analyseSqlDTO) {
        ExecutePlanDTO executePlanDTO = new ExecutePlanDTO();

        executePlanDTO.setDbName(analyseSqlDTO.getDbName());
        executePlanDTO.setSql(analyseSqlDTO.getSql());
        executePlanDTO.setSqlType(analyseSqlDTO.getSqlType());
        executePlanDTO.setTableNames(analyseSqlDTO.getTableNames());
        executePlanDTO.setFields(analyseSqlDTO.getFields());
        //TODO 这里选择索引的策略特别复杂，暂直接使用主键（注意：这里直接使用第一张表的主键）
        executePlanDTO.setIndexColumn(StringUtils.givePrimaryKeyByTableName(analyseSqlDTO.getDbName(), analyseSqlDTO.getTableNames().get(0)));

        return executePlanDTO;
    }
}
