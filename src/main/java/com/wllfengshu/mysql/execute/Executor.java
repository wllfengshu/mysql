package com.wllfengshu.mysql.execute;

import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.model.dto.ExecutePlanDTO;
import com.wllfengshu.mysql.model.entity.ResultSet;
import com.wllfengshu.mysql.model.enumerate.StorageType;
import com.wllfengshu.mysql.storage.StorageEngine;
import com.wllfengshu.mysql.storage.impl.Innodb;
import com.wllfengshu.mysql.storage.impl.MyIsam;
import com.wllfengshu.mysql.utils.StringUtils;
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

    public ResultSet start(ExecutePlanDTO executePlanDTO) throws CustomException {
        StorageEngine storageEngine = chooseStorage(executePlanDTO);
        return execute(executePlanDTO, storageEngine);
    }

    /**
     * 选择存储引擎
     * @param executePlanDTO
     * @return
     * @throws CustomException
     */
    private StorageEngine chooseStorage(ExecutePlanDTO executePlanDTO) throws CustomException{
        //TODO 这里根据第一张表来选择存储引擎（也就是说不支持不同存储引擎的表进行关联查询）
        StorageType storageType = StringUtils.giveStorageEngineNameByTableName(executePlanDTO.getDbName(),executePlanDTO.getTableNames().get(0));
        switch (storageType) {
            case Innodb:
                return new Innodb();
            case MyIsam:
                return new MyIsam();
            default:
                throw new CustomException("无法确定存储引擎", CustomException.ExceptionName.ILLEGAL_PARAM);
        }
    }

    /**
     * 操作存储引擎
     * @param executePlanDTO
     * @param storageEngine
     * @return
     * @throws CustomException
     */
    private ResultSet execute(ExecutePlanDTO executePlanDTO, StorageEngine storageEngine) throws CustomException{
        switch (executePlanDTO.getSqlType()) {
            case Insert:
                return storageEngine.insert(executePlanDTO);
            case Delete:
                return storageEngine.delete(executePlanDTO);
            case Update:
                return storageEngine.update(executePlanDTO);
            case Select:
                return storageEngine.select(executePlanDTO);
            default:
                throw new CustomException("无法确定操作", CustomException.ExceptionName.ILLEGAL_PARAM);
        }
    }
}
