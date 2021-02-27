package com.wllfengshu.mysql.storage.impl;

import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.model.dto.ExecutePlanDTO;
import com.wllfengshu.mysql.model.entity.ResultSet;
import com.wllfengshu.mysql.storage.StorageEngine;
import com.wllfengshu.mysql.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.RandomAccessFile;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * innodb引擎
 *
 * TODO 注意：暂时不考虑锁
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class Innodb implements StorageEngine {

    @Override
    public ResultSet insert(ExecutePlanDTO executePlanDTO) throws CustomException {
        //1 找到对应的文件
        File tableFile = FileUtils.giveTableFile(executePlanDTO.getDbName(), executePlanDTO.getTableNames().get(0));
        //2 从insert语句中解析出数据
        String pending = FileUtils.giveContentByInsertSql(executePlanDTO.getSql());
        //3 把数据插入对应的db、对应的ibd文件中
        try (RandomAccessFile file = new RandomAccessFile(tableFile, "rw")) {
            file.seek(file.length());
            file.write(pending.getBytes(UTF_8));
        }catch (Exception e){
            throw new CustomException("写数据库失败", CustomException.ExceptionName.FAIL_INSERT);
        }
        return new ResultSet(true);
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
        File tableFile = FileUtils.giveTableFile(executePlanDTO.getDbName(), executePlanDTO.getTableNames().get(0));
        //2 找到对应的行
//        FileUtils.readFile()
        return null;
    }
}
