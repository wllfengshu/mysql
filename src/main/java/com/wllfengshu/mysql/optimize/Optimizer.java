package com.wllfengshu.mysql.optimize;

import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.execute.Executor;
import com.wllfengshu.mysql.model.dto.AnalyseSqlDTO;
import com.wllfengshu.mysql.model.dto.ExecutePlanDTO;
import com.wllfengshu.mysql.model.entity.ResultSet;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * 优化器
 *
 * 作用：
 * 1、生成执行计划，选择索引
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class Optimizer {

    @NonNull
    private Executor executor;

    public ResultSet start(AnalyseSqlDTO analyseSqlDTO) throws CustomException {
        ExecutePlanDTO executePlanDTO = optimize(analyseSqlDTO);
        return executor.start(executePlanDTO);
    }

    /**
     * 生成执行计划，选择索引
     * @param analyseSqlDTO
     * @return
     */
    private ExecutePlanDTO optimize(AnalyseSqlDTO analyseSqlDTO) {
        ExecutePlanDTO executePlanDTO = new ExecutePlanDTO();

        executePlanDTO.setSql(analyseSqlDTO.getSql());
        executePlanDTO.setSqlType(analyseSqlDTO.getSqlType());
        executePlanDTO.setTableNames(analyseSqlDTO.getTableNames());
        executePlanDTO.setFields(analyseSqlDTO.getFields());
        executePlanDTO.setIndexColumn(null);// 选择索引

        return executePlanDTO;
    }
}
