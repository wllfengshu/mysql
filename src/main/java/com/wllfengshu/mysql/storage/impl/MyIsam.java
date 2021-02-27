package com.wllfengshu.mysql.storage.impl;

import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.model.dto.ExecutePlanDTO;
import com.wllfengshu.mysql.model.entity.ResultSet;
import com.wllfengshu.mysql.storage.StorageEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * TODO 暂不实现
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
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
