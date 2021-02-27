package com.wllfengshu.mysql.utils;

import com.alibaba.fastjson.JSON;
import com.wllfengshu.mysql.common.Cache;
import com.wllfengshu.mysql.common.Constant;
import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.model.enumerate.SqlType;
import com.wllfengshu.mysql.model.enumerate.StorageType;
import org.junit.Test;

import java.util.List;

public class StringUtilsTest {

    @Test
    public void testGiveDbNameByUseDbSql() throws CustomException {
        String useDbSql = "use test;";
        String dbName = StringUtils.giveDbNameByUseDbSql(useDbSql);
        System.out.println(dbName);
        assert "test".equals(dbName);

        useDbSql = "use test ;";
        dbName = StringUtils.giveDbNameByUseDbSql(useDbSql);
        System.out.println(dbName);
        assert "test".equals(dbName);

        useDbSql = " use test ;";
        dbName = StringUtils.giveDbNameByUseDbSql(useDbSql);
        System.out.println(dbName);
        assert "test".equals(dbName);

        useDbSql = "use test ; ";
        dbName = StringUtils.giveDbNameByUseDbSql(useDbSql);
        System.out.println(dbName);
        assert "test".equals(dbName);

        useDbSql = "use test; ";
        dbName = StringUtils.giveDbNameByUseDbSql(useDbSql);
        System.out.println(dbName);
        assert "test".equals(dbName);

        useDbSql = "use test ";
        dbName = StringUtils.giveDbNameByUseDbSql(useDbSql);
        System.out.println(dbName);
        assert "test".equals(dbName);
    }

    @Test
    public void testGiveTableNames() {
        String sql = "insert into t_user (id,username,password,nickname) values (1,'aa','bb','cc'),(2,'aa2','bb2','cc2');";
        List<String> tableNames = StringUtils.giveTableNames(sql, SqlType.Insert);
        assert "t_user".equals(tableNames.get(0));

        sql = "delete from t_user where id='2';";
        tableNames = StringUtils.giveTableNames(sql, SqlType.Delete);
        assert "t_user".equals(tableNames.get(0));

        sql = "update t_user set username='aaaaaa',nickname='cccccc' where id=1;";
        tableNames = StringUtils.giveTableNames(sql, SqlType.Update);
        assert "t_user".equals(tableNames.get(0));

        sql = "select * from t_user where id=1;";
        tableNames = StringUtils.giveTableNames(sql, SqlType.Select);
        assert "t_user".equals(tableNames.get(0));
    }

    @Test
    public void testGiveFieldsBySql() {
        String sql = "select * from t_user where id=1;";
        List<String> fields = StringUtils.giveFieldsBySql(sql);
        assert fields.size()==1;
        assert "*".equals(fields.get(0));

        sql = "select id,username from t_user where id=1;";
        fields = StringUtils.giveFieldsBySql(sql);
        assert fields.size()==2;
        assert "id".equals(fields.get(0));
        assert "username".equals(fields.get(1));
    }

    @Test
    public void testGiveStorageEngineNameByTableName() throws CustomException{
        StorageType storageType = StringUtils.giveStorageEngineNameByTableName("test", "t_user");
        assert storageType == StorageType.Innodb;
    }

    @Test
    public void testReadFile() throws CustomException{
        String fileContent = StringUtils.readFile("data/test/t_user.frm");
        System.out.println(fileContent);
        assert null != fileContent;
        assert fileContent.length() > 0;
        assert fileContent.contains("CREATE TABLE");
        assert fileContent.contains("ENGINE");
    }

    @Test
    public void testReadDirForDirName() throws CustomException{
        List<String> dbNames = StringUtils.readDirForDirName(Constant.DATA_PATH);
        assert null != dbNames;
        System.out.println(JSON.toJSONString(dbNames));
    }

    @Test
    public void testReadDirForFileName() throws CustomException{
        List<String> fileNames = StringUtils.readDirForFileName(Constant.DATA_PATH + "/test/", ".frm");
        assert null != fileNames;
        System.out.println(JSON.toJSONString(fileNames));
    }

    @Test
    public void testGivePrimaryKeyByTableName() throws CustomException{
        Cache.DB_TABLE_FRM_MAP.put("test-t_user","CREATE TABLE `t_user` (  `id` int(11) NOT NULL AUTO_INCREMENT,  `username` varchar(64) NOT NULL DEFAULT '',  `password` varchar(255) NOT NULL DEFAULT '',  `nickname` varchar(128) NOT NULL DEFAULT '1',  PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;");
        String pk = StringUtils.givePrimaryKeyByTableName("test", "t_user");
        System.out.println(pk);
        assert "id".equals(pk);
    }
}