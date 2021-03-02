package com.wllfengshu.mysql.analyse;

import com.wllfengshu.mysql.model.dto.AnalyseSqlDTO;
import com.wllfengshu.mysql.model.dto.PendingSqlDTO;
import com.wllfengshu.mysql.model.enumerate.SqlType;
import com.wllfengshu.mysql.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AnalyseHandler {

    /**
     * 词法分析
     *
     * @param pendingSqlDTO
     */
    public void analyseForLexical(PendingSqlDTO pendingSqlDTO) {
        //TODO 分析单词是否拼写错误
    }

    /**
     * 语法分析
     *
     * @param pendingSqlDTO
     */
    public void analyseForGrammatical(PendingSqlDTO pendingSqlDTO) {
        //TODO 检查是否有语法错误
    }

    /**
     * 分析待执行的sql对象，并返回处理后的AnalyseSqlDTO对象
     *
     * @param pendingSqlDTO
     * @return
     */
    public AnalyseSqlDTO analyse(PendingSqlDTO pendingSqlDTO) {
        AnalyseSqlDTO analyseSqlDTO = new AnalyseSqlDTO();

        analyseSqlDTO.setDbName(pendingSqlDTO.getDbName());
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
