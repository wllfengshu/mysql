package com.wllfengshu.mysql.connect;

import com.wllfengshu.mysql.analyse.Analyzer;
import com.wllfengshu.mysql.configs.EnvConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * 连接器
 *
 * 作用：
 * 1、建立连接
 * 2、解析出表名、sql类型等数据
 * 3、鉴权
 */
@Order(2)
@Slf4j
@Configuration
@RequiredArgsConstructor
public class Connector implements CommandLineRunner {

    @NonNull
    private ExecutorService executorService;
    @NonNull
    private EnvConfig envConfig;
    @NonNull
    private Analyzer analyzer;

    @Override
    public void run(String... args) {
        log.info("正在启动mysql...");
        try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(envConfig.getPort()))){
            while (true) {
                log.info("mysql启动完毕");
                Socket socket = serverSocket.accept();
                executorService.execute(new ConnectHandler(analyzer, socket));
            }
        }catch (Exception e) {
            log.error("mysql启动失败");
        }
    }
}
