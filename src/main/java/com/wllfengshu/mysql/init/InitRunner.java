package com.wllfengshu.mysql.init;

import com.alibaba.fastjson.JSON;
import com.wllfengshu.mysql.common.Cache;
import com.wllfengshu.mysql.common.Constant;
import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统初始化
 *
 * @author wangll
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class InitRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.info("正在初始化");
        try {
            cacheDbTableFrmMap();
        }catch (Exception e){
            log.error("初始化失败，程序退出", e);
            System.exit(-1);
        }
        log.info("初始化完毕");
    }

    /**
     * 把所有的表
     */
    private void cacheDbTableFrmMap() throws CustomException {
        log.info("正在缓存所有的表结构");
        List<String> dbNames = StringUtils.readDirForDirName(Constant.DATA_PATH);
        for (String dbName : dbNames){
            List<String> tableNames = StringUtils.readDirForFileName(Constant.DATA_PATH + '/' + dbName, Constant.DATA_FILE_FRM);
            for (String tableName : tableNames){
                String tableContent = StringUtils.readFile(Constant.DATA_PATH + '/' + dbName + '/' + tableName + Constant.DATA_FILE_FRM);
                Cache.DB_TABLE_FRM_MAP.put(dbName + '-' + tableName, tableContent);
            }
        }
        log.info("表结构缓存完毕" + JSON.toJSONString(Cache.DB_TABLE_FRM_MAP));
    }
}