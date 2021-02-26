package com.wllfengshu.mysql.connect;

import com.wllfengshu.mysql.analyse.Analyzer;
import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.model.dto.PendingSqlDTO;
import com.wllfengshu.mysql.model.enumerate.SqlType;
import com.wllfengshu.mysql.model.vo.SqlVO;
import com.wllfengshu.mysql.model.vo.UserVO;
import com.wllfengshu.mysql.utils.StringUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * 连接器
 *
 * 作用：
 * 1、建立连接
 * 2、解析出表名、sql类型等数据
 * 3、鉴权
 */
@Slf4j
public class Connector {

    @NonNull
    private Analyzer analyzer;

    private Connector() throws CustomException {
        //1 建立连接
        connect(new UserVO());
        //2 选择数据库
        String dbName = giveUseDb(null);
        //3 获取sql信息
        PendingSqlDTO pendingSqlDTO = giveSqlInfo(new SqlVO());
        pendingSqlDTO.setDbName(dbName);
        //4 鉴权
        authentication(pendingSqlDTO);
        //5 run
        analyzer.start(pendingSqlDTO);
    }

    /**
     * 建立连接
     * @param userVO
     */
    private void connect(UserVO userVO) {

    }

    /**
     * 选择使用哪个数据库
     * @param useDbSql
     */
    private String giveUseDb(String useDbSql) {
        return StringUtils.giveDbNameByUseDbSql(useDbSql);
    }

    /**
     * 获取sql信息
     * @param sqlVO
     * @return
     */
    private PendingSqlDTO giveSqlInfo(SqlVO sqlVO) throws CustomException {
        PendingSqlDTO pendingSqlDTO = new PendingSqlDTO();

        String sql = sqlVO.getSql();
        pendingSqlDTO.setSql(sql);
        if (sql.startsWith("insert")){
            pendingSqlDTO.setSqlType(SqlType.Insert);
        }else if (sql.startsWith("delete")){
            pendingSqlDTO.setSqlType(SqlType.Delete);
        }else if (sql.startsWith("update")){
            pendingSqlDTO.setSqlType(SqlType.Update);
        }else if (sql.startsWith("select")){
            pendingSqlDTO.setSqlType(SqlType.Select);
        }else {
            throw new CustomException("无法确定sql类型", CustomException.ExceptionName.ILLEGAL_PARAM);
        }
        pendingSqlDTO.setTableNames(StringUtils.giveTableNames(sql));

        return pendingSqlDTO;
    }

    /**
     * 鉴权
     * @param pendingSqlDTO
     */
    private void authentication(PendingSqlDTO pendingSqlDTO) {

    }
}
