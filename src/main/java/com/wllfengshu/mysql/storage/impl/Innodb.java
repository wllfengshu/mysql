package com.wllfengshu.mysql.storage.impl;

import com.wllfengshu.mysql.common.Constant;
import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.model.dto.ExecutePlanDTO;
import com.wllfengshu.mysql.model.entity.ResultSet;
import com.wllfengshu.mysql.storage.StorageEngine;
import com.wllfengshu.mysql.utils.StringUtils;

import java.io.File;

public class Innodb implements StorageEngine {

    @Override
    public ResultSet insert(ExecutePlanDTO executePlanDTO) throws CustomException {
        //1 找到对应的文件
        String tableFilePath = Constant.DATA_PATH + "/" + executePlanDTO.getDbName() + "/" + executePlanDTO.getTableNames().get(0) + Constant.DATA_FILE_IBD;
        File tableFile = StringUtils.giveFile(tableFilePath);
        //2 从insert语句中解析出数据

        //3 把数据插入对应的db、对应的ibd文件中

        return null;
    }

    @Override
    public ResultSet delete(ExecutePlanDTO executePlanDTO) throws CustomException {
        //1 找到对应的文件

        //2 找到对应的行

        //3 删除对应的行

        return null;
    }

    @Override
    public ResultSet update(ExecutePlanDTO executePlanDTO) throws CustomException {
        //1 找到对应的文件

        //2 找到对应的行

        //3 更新数据

        //4 重新写入

        return null;
    }

    @Override
    public ResultSet select(ExecutePlanDTO executePlanDTO) throws CustomException {
        //1 找到对应的文件

        //2 找到对应的行

        return null;
    }
}
