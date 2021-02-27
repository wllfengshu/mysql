package com.wllfengshu.mysql.utils;

import com.alibaba.fastjson.JSON;
import com.wllfengshu.mysql.common.Constant;
import com.wllfengshu.mysql.exception.CustomException;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class FileUtilsTest {

    @Test
    public void testReadFile() throws CustomException {
        String fileContent = FileUtils.readFile("data/test/t_user.frm");
        System.out.println(fileContent);
        assert null != fileContent;
        assert fileContent.length() > 0;
        assert fileContent.contains("CREATE TABLE");
        assert fileContent.contains("ENGINE");
    }

    @Test
    public void testReadDirForDirName() throws CustomException{
        List<String> dbNames = FileUtils.readDirForDirName(Constant.DATA_PATH);
        assert null != dbNames;
        System.out.println(JSON.toJSONString(dbNames));
    }

    @Test
    public void testReadDirForFileName() throws CustomException{
        List<String> fileNames = FileUtils.readDirForFileName(Constant.DATA_PATH + "/test/", ".frm");
        assert null != fileNames;
        System.out.println(JSON.toJSONString(fileNames));
    }

    @Test
    public void testGiveFile() throws CustomException{
        File file = FileUtils.giveFile(Constant.DATA_PATH + "/test");
        assert file.exists();
        assert file.isDirectory();
    }

    @Test
    public void testGiveTableFile() throws CustomException{
        File file = FileUtils.giveTableFile("test", "t_user");
        assert file.exists();
        assert file.isFile();
    }

    @Test
    public void testGiveContentByInsertSql() throws CustomException{
        String sql = "insert into t_user (id,username,password,nickname) values (1,'aa','bb','cc'),(2,'aa2','bb2','cc2');";
        String content = FileUtils.giveContentByInsertSql(sql);
        System.out.println(content);
        assert content.startsWith("1");

        sql = "insert into t_user (id,username,password,nickname) values (1,'aa','bb','cc');";
        content = FileUtils.giveContentByInsertSql(sql);
        System.out.println(content);
        assert content.startsWith("1");

        sql = "insert into t_user (id,username,password,nickname) values (1,'aa','bb','cc') ";
        content = FileUtils.giveContentByInsertSql(sql);
        System.out.println(content);
        assert content.startsWith("1");
    }
}