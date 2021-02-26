package com.wllfengshu.mysql.storage;

import com.wllfengshu.mysql.model.dto.ExecutePlanDTO;
import com.wllfengshu.mysql.model.entity.ResultSet;

/**
 * 存储引擎
 */
public interface StorageEngine {

    ResultSet insert(ExecutePlanDTO executePlanDTO);
    ResultSet delete(ExecutePlanDTO executePlanDTO);
    ResultSet update(ExecutePlanDTO executePlanDTO);
    ResultSet select(ExecutePlanDTO executePlanDTO);
}
