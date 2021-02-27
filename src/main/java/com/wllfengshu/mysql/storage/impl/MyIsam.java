package com.wllfengshu.mysql.storage.impl;

import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.model.dto.ExecutePlanDTO;
import com.wllfengshu.mysql.model.entity.ResultSet;
import com.wllfengshu.mysql.storage.StorageEngine;

/**
 * TODO 暂不实现
 */
public class MyIsam implements StorageEngine {

    @Override
    public ResultSet insert(ExecutePlanDTO executePlanDTO) throws CustomException {
        return null;
    }

    @Override
    public ResultSet delete(ExecutePlanDTO executePlanDTO) throws CustomException {
        return null;
    }

    @Override
    public ResultSet update(ExecutePlanDTO executePlanDTO) throws CustomException {
        return null;
    }

    @Override
    public ResultSet select(ExecutePlanDTO executePlanDTO) throws CustomException {
        return null;
    }
}
