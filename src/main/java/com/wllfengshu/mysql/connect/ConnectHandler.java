package com.wllfengshu.mysql.connect;

import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.model.dto.PendingSqlDTO;
import com.wllfengshu.mysql.model.enumerate.SqlType;
import com.wllfengshu.mysql.model.vo.SqlVO;
import com.wllfengshu.mysql.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ConnectHandler {

    /**
     * 选择使用哪个数据库
     *
     * @param useDbSql
     */
    public String giveUseDb(String useDbSql) throws CustomException {
        return StringUtils.giveDbNameByUseDbSql(useDbSql);
    }

    /**
     * 获取sql信息
     *
     * @param sqlVO
     * @return
     */
    public PendingSqlDTO giveSqlInfo(SqlVO sqlVO) throws CustomException {
        PendingSqlDTO pendingSqlDTO = new PendingSqlDTO();

        String sql = sqlVO.getSql();
        pendingSqlDTO.setSql(sql);
        if (sql.startsWith("insert")) {
            pendingSqlDTO.setSqlType(SqlType.Insert);
        } else if (sql.startsWith("delete")) {
            pendingSqlDTO.setSqlType(SqlType.Delete);
        } else if (sql.startsWith("update")) {
            pendingSqlDTO.setSqlType(SqlType.Update);
        } else if (sql.startsWith("select")) {
            pendingSqlDTO.setSqlType(SqlType.Select);
        } else {
            throw new CustomException("无法确定sql类型", CustomException.ExceptionName.ILLEGAL_PARAM);
        }
        pendingSqlDTO.setTableNames(StringUtils.giveTableNames(sql, pendingSqlDTO.getSqlType()));

        return pendingSqlDTO;
    }

    /**
     * 鉴权
     *
     * @param pendingSqlDTO
     */
    public void authentication(PendingSqlDTO pendingSqlDTO) {
        //TODO 根据pendingSqlDTO中数据库名和表名，去读取sys库中的表，来判断是否有权限
    }
}
