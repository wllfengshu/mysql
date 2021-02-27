package com.wllfengshu.mysql.storage;

import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.model.dto.ExecutePlanDTO;
import com.wllfengshu.mysql.model.entity.ResultSet;

/**
 * 存储引擎
 */
public interface StorageEngine {

    ResultSet insert(ExecutePlanDTO executePlanDTO) throws CustomException;
    ResultSet delete(ExecutePlanDTO executePlanDTO) throws CustomException ;
    ResultSet update(ExecutePlanDTO executePlanDTO) throws CustomException ;
    ResultSet select(ExecutePlanDTO executePlanDTO) throws CustomException ;
}
