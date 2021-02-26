package com.wllfengshu.mysql.analyse;

import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.model.dto.AnalyseSqlDTO;
import com.wllfengshu.mysql.model.dto.PendingSqlDTO;
import com.wllfengshu.mysql.model.entity.ResultSet;
import com.wllfengshu.mysql.model.enumerate.SqlType;
import com.wllfengshu.mysql.optimize.Optimizer;
import com.wllfengshu.mysql.utils.StringUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 分析器
 *
 * 作用：
 * 1、词法分析
 * 2、语法分析
 * 3、判断sql类型
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class Analyzer {

    @NonNull
    private Optimizer optimizer;

    public ResultSet start(PendingSqlDTO pendingSqlDTO) throws CustomException {
        analyseForLexical(pendingSqlDTO);
        analyseForGrammatical(pendingSqlDTO);
        AnalyseSqlDTO analyseSqlDTO = analyse(pendingSqlDTO);
        return optimizer.start(analyseSqlDTO);
    }

    /**
     * 词法分析
     * @param pendingSqlDTO
     */
    private void analyseForLexical(PendingSqlDTO pendingSqlDTO) {
        //TODO
    }

    /**
     * 语法分析
     * @param pendingSqlDTO
     */
    private void analyseForGrammatical(PendingSqlDTO pendingSqlDTO) {
        //TODO
    }

    /**
     * 分析待执行的sql对象，并返回处理后的AnalyseSqlDTO对象
     * @param pendingSqlDTO
     * @return
     */
    private AnalyseSqlDTO analyse(PendingSqlDTO pendingSqlDTO) {
        AnalyseSqlDTO analyseSqlDTO = new AnalyseSqlDTO();

        analyseSqlDTO.setSql(pendingSqlDTO.getSql());
        analyseSqlDTO.setSqlType(pendingSqlDTO.getSqlType());
        analyseSqlDTO.setTableNames(pendingSqlDTO.getTableNames());
        if (pendingSqlDTO.getSqlType() == SqlType.Select) {
            String sql = pendingSqlDTO.getSql();
            List<String> fields = StringUtils.giveFieldsBySql(sql);
            analyseSqlDTO.setFields(fields);
        }

        return analyseSqlDTO;
    }
}
