package com.wllfengshu.mysql.analyse;

import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.model.dto.AnalyseSqlDTO;
import com.wllfengshu.mysql.model.dto.PendingSqlDTO;
import com.wllfengshu.mysql.model.entity.ResultSet;
import com.wllfengshu.mysql.optimize.Optimizer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

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
    private AnalyseHandler analyseHandler;
    @NonNull
    private Optimizer optimizer;

    public ResultSet start(PendingSqlDTO pendingSqlDTO) throws CustomException {
        analyseHandler.analyseForLexical(pendingSqlDTO);
        analyseHandler.analyseForGrammatical(pendingSqlDTO);
        AnalyseSqlDTO analyseSqlDTO = analyseHandler.analyse(pendingSqlDTO);
        return optimizer.start(analyseSqlDTO);
    }
}
