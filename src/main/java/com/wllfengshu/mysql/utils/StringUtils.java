package com.wllfengshu.mysql.utils;

import com.wllfengshu.mysql.common.Cache;
import com.wllfengshu.mysql.exception.CustomException;
import com.wllfengshu.mysql.model.enumerate.SqlType;
import com.wllfengshu.mysql.model.enumerate.StorageType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 字符串工具类
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    /**
     * 根据使用哪个数据库的sql，返回选择的数据库名
     *
     * @param useDbSql
     * @return
     */
    public static String giveDbNameByUseDbSql(String useDbSql) throws CustomException{
        useDbSql = useDbSql.trim();
        if (!useDbSql.startsWith("use")){
            throw new CustomException("选择数据库时，必须使用use关键字", CustomException.ExceptionName.MUST_USE_DB);
        }
        return useDbSql.split("use")[1].trim().split(";")[0].trim();
    }

    /**
     * 根据sql脚本获取表名
     * eg:
     * insert into t_user (id,username,password,nickname) values (1,'aa','bb','cc'),(2,'aa2','bb2','cc2');
     * delete from t_user where id='2';
     * update t_user set username='aaaaaa',nickname='cccccc' where id=1;
     * select * from t_user where id=1;
     *
     * TODO 这里如果是查询，则暂时只考虑单表
     * @param sql
     * @param sqlType
     * @return
     */
    public static List<String> giveTableNames(String sql, SqlType sqlType) {
        List<String> tableNames = new ArrayList<>();
        switch (sqlType) {
            case Insert:
                tableNames.add(sql.split("into")[1].split("\\(")[0].trim());
                break;
            case Update:
                tableNames.add(sql.split("update")[1].split("set")[0].trim());
                break;
            case Delete:
            case Select:
                tableNames.add(sql.split("from")[1].split("where")[0].trim());
                break;
            default:
        }
        return tableNames;
    }

    /**
     * 根据sql脚本获取字段（仅select的sql）
     * eg:
     * select * from t_user where id=1;
     * select id,username from t_user where id=1;
     *
     * @param sql
     * @return
     */
    public static List<String> giveFieldsBySql(String sql) {
        sql = sql.trim();
        List<String> fields = new ArrayList<>();
        if (sql.startsWith("select")) {
            String fieldsString = sql.split("select")[1].split("from")[0].trim();
            if ("*".equals(fieldsString)){
                //TODO 这里暂时直接存*
                fields.add("*");
            }else {
                for (String f : fieldsString.split(",")) {
                    fields.add(f.trim());
                }
            }
        }
        return fields;
    }

    /**
     * 根据表名获取存储引擎名
     *
     * @param dbName
     * @param tableName
     * @return
     */
    public static StorageType giveStorageEngineNameByTableName(String dbName, String tableName) throws CustomException{
        String frmString = Cache.DB_TABLE_FRM_MAP.get(dbName + '-' + tableName);
        String storageName = frmString.split("ENGINE=")[1].split(" ")[0];
        return "MyISAM".equals(storageName) ? StorageType.MyIsam : StorageType.Innodb;
    }

    /**
     * 根据表名获取主键列
     *
     * @param dbName
     * @param tableName
     * @return
     */
    public static String givePrimaryKeyByTableName(String dbName, String tableName) {
        String frmString = Cache.DB_TABLE_FRM_MAP.get(dbName + '-' + tableName);
        String primaryKey = frmString.split("PRIMARY KEY \\(")[1].split("\\)")[0].replace("`","").trim();
        return primaryKey;
    }

    /**
     * 读取文件为字符串
     *
     * @param path
     * @return
     */
    public static String readFile(String path) throws CustomException{
        Resource resource = new ClassPathResource(path);
        if (!resource.exists()) {
            throw new CustomException("读取文件失败-" + path + "文件不存在", CustomException.ExceptionName.FAIL_READ_FILE);
        }
        StringBuilder sb = new StringBuilder();
        try (InputStream input = resource.getInputStream();
             Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(reader)) {
            String temp;
            while (null != (temp = br.readLine())) {
                //TODO 排除空行、#开头的行
                if (!"".equals(temp) && !"\n".equals(temp) && !"\r\n".equals(temp) && !temp.startsWith("#")) {
                    sb.append(temp);
                }
            }
        } catch (Exception e) {
            throw new CustomException("读取文件失败", CustomException.ExceptionName.FAIL_READ_FILE);
        }
        return sb.toString();
    }

    /**
     * 读取路径下的所有文件夹名
     *
     * @param path
     * @return
     */
    public static List<String> readDirForDirName(String path) throws CustomException{
        List<String> result = new ArrayList<>();
        File dir = StringUtils.giveFile(path);
        if (!dir.isDirectory()){
            return result;
        }
        for (File f : Objects.requireNonNull(dir.listFiles())){
            if (f.isDirectory()){
                result.add(f.getName());
            }
        }
        return result;
    }

    /**
     * 读取路径下的所有文件名(不带后缀)
     *
     * @param path
     * @param suffix 指定后缀
     * @return
     */
    public static List<String> readDirForFileName(String path, String suffix) throws CustomException{
        List<String> result = new ArrayList<>();
        File dir = StringUtils.giveFile(path);
        if (!dir.isDirectory()){
            return result;
        }
        File[] files = Objects.requireNonNull(dir.listFiles());
        for (File f : files) {
            if (!f.isDirectory()) {
                String fName = f.getName();
                if (org.apache.commons.lang3.StringUtils.isEmpty(suffix) || fName.endsWith(suffix)){
                    result.add(fName.substring(0, fName.lastIndexOf(".")));
                }
            }
        }
        return result;
    }

    /**
     * 获取文件
     *
     * @param path
     * @return
     * @throws CustomException
     */
    public static File giveFile(String path) throws CustomException{
        Resource resource = new ClassPathResource(path);
        if (!resource.exists()) {
            throw new CustomException("无法找到文件或目录-" + path, CustomException.ExceptionName.NOT_FOUND_FILE_OR_DIR);
        }
        try {
            return resource.getFile();
        }catch (Exception e) {
            throw new CustomException("获取文件或目录失败", CustomException.ExceptionName.NOT_FOUND_FILE_OR_DIR);
        }
    }
}
