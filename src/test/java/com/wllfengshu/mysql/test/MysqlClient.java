package com.wllfengshu.mysql.test;

import com.wllfengshu.mysql.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author wangll
 * @date 2021-03-02 19:42
 */
@Slf4j
public class MysqlClient {

    private static String ip = "127.0.0.1";
    private static int port = 3306;

    public static void main(String[] args) throws Exception{
        Socket client = new Socket(ip, port);
        OutputStream os = client.getOutputStream();
        os.write("select * from t_user".getBytes("UTF-8"));
        os.flush();

        InputStream is = client.getInputStream();
        String resultSet = StringUtils.inputStream2String(is);
        log.info("resultSet"+resultSet);

        is.close();
        os.close();
    }
}
