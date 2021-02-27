package com.wllfengshu.mysql.connect;

import com.wllfengshu.mysql.analyse.Analyzer;
import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.model.dto.PendingSqlDTO;
import com.wllfengshu.mysql.model.vo.SqlVO;
import com.wllfengshu.mysql.model.vo.UserVO;
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
    private ConnectHandler connectHandler;
    @NonNull
    private Analyzer analyzer;

    private Connector() throws CustomException {
        //1 建立连接
        connect(new UserVO());
        //2 选择数据库
        String dbName = connectHandler.giveUseDb(null);
        //3 获取sql信息
        PendingSqlDTO pendingSqlDTO = connectHandler.giveSqlInfo(new SqlVO());
        pendingSqlDTO.setDbName(dbName);
        //4 鉴权
        connectHandler.authentication(pendingSqlDTO);
        //5 run
        analyzer.start(pendingSqlDTO);
    }

    /**
     * 建立连接
     *
     * @param userVO
     */
    private void connect(UserVO userVO) {

    }
}
