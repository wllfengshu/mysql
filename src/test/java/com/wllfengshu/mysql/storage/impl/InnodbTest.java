package com.wllfengshu.mysql.storage.impl;

import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.model.dto.ExecutePlanDTO;
import com.wllfengshu.mysql.model.entity.ResultSet;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class InnodbTest {

    private Innodb innodb = new Innodb();
    private ExecutePlanDTO executePlanDTO = new ExecutePlanDTO();

    @Before
    public void setUp() {
        executePlanDTO.setDbName("test");
        List<String> tableNames = new ArrayList<>();
        tableNames.add("t_user");
        executePlanDTO.setTableNames(tableNames);
    }

    @Test
    public void insert() throws CustomException {
        executePlanDTO.setSql("insert into t_user (id,username,password,nickname) values (1,'aa','bb','cc'),(2,'aa2','bb2','cc2');");
        ResultSet insert = innodb.insert(executePlanDTO);
        assert insert.getSuccess();
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void select() throws CustomException{
        executePlanDTO.setSql("select * from t_user where id=1;");
        ResultSet select = innodb.select(executePlanDTO);
        System.out.println(select.getContent());
        assert select.getSuccess();
        assert !select.getContent().isEmpty();
    }

    @Test
    public void testRandomAccessFile() throws CustomException{
        File tableFile = new File("E:\\home\\App\\aaaa.txt");
        try (RandomAccessFile file = new RandomAccessFile(tableFile, "rw")) {
            file.seek(file.length());
            file.write("aaaa你好".getBytes("UTF-8"));
        }catch (Exception e){
            throw new CustomException("写数据库失败", CustomException.ExceptionName.FAIL_INSERT);
        }
    }
}