package com.wllfengshu.mysql.utils;

import com.wllfengshu.mysql.common.Constant;
import com.wllfengshu.mysql.exception.CustomException;
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
 * 文件工具类
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtils {

    /**
     * 读取文件为字符串
     *
     * @param path
     * @return
     */
    public static String readFile(String path) throws CustomException {
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
    public static List<String> readDirForDirName(String path) throws CustomException {
        List<String> result = new ArrayList<>();
        File dir = FileUtils.giveFile(path);
        if (!dir.isDirectory()) {
            return result;
        }
        for (File f : Objects.requireNonNull(dir.listFiles())) {
            if (f.isDirectory()) {
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
    public static List<String> readDirForFileName(String path, String suffix) throws CustomException {
        List<String> result = new ArrayList<>();
        File dir = FileUtils.giveFile(path);
        if (!dir.isDirectory()) {
            return result;
        }
        File[] files = Objects.requireNonNull(dir.listFiles());
        for (File f : files) {
            if (!f.isDirectory()) {
                String fName = f.getName();
                if (org.apache.commons.lang3.StringUtils.isEmpty(suffix) || fName.endsWith(suffix)) {
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
    public static File giveFile(String path) throws CustomException {
        Resource resource = new ClassPathResource(path);
        if (!resource.exists()) {
            throw new CustomException("无法找到文件或目录-" + path, CustomException.ExceptionName.NOT_FOUND_FILE_OR_DIR);
        }
        try {
            return resource.getFile();
        } catch (Exception e) {
            throw new CustomException("获取文件或目录失败", CustomException.ExceptionName.NOT_FOUND_FILE_OR_DIR);
        }
    }

    /**
     * 根据executePlanDTO获取表文件对象
     *
     * @param dbName
     * @param tableName
     * @return
     * @throws CustomException
     */
    public static File giveTableFile(String dbName, String tableName) throws CustomException {
        return FileUtils.giveFile(Constant.DATA_PATH + "/" + dbName + "/" + tableName + Constant.DATA_FILE_IBD);
    }

    /**
     * 从insert语句中解析出数据（字段之间空格分隔；多条数据之间使用\n分割）
     * eg:
     * insert into t_user (id,username,password,nickname) values (1,'aa','bb','cc'),(2,'aa2','bb2','cc2');
     * TODO 注意：这里没有考虑字段的先后顺序
     *
     * @param sql
     * @return
     */
    public static String giveContentByInsertSql(String sql) {
        StringBuffer sb = new StringBuffer();
        String valuesSql = sql.split("values")[1].split(";")[0];
        String[] values = valuesSql.split("\\),\\(");
        for (String v : values) {
            v = v.replace("(", "").replace(")", "");
            String[] fields = v.split(",");
            for (String f : fields) {
                sb.append(f.replace("'", "").replace("\\\"", "").trim() + " ");
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("\n");
        }
        return sb.toString();
    }
}
