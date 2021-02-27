package com.wllfengshu.mysql.execute;

import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.model.dto.ExecutePlanDTO;
import com.wllfengshu.mysql.model.entity.ResultSet;
import com.wllfengshu.mysql.storage.StorageEngine;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * 执行器
 *
 * 作用：
 * 1、选择存储引擎
 * 2、操作存储引擎（CURD）
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class Executor {

    @NonNull
    private ExecuteHandler executeHandler;

    public ResultSet start(ExecutePlanDTO executePlanDTO) throws CustomException {
        StorageEngine storageEngine = executeHandler.chooseStorage(executePlanDTO);
        return executeHandler.execute(executePlanDTO, storageEngine);
    }
}
