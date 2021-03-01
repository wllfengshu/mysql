package com.wllfengshu.mysql.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 线程池配置
 *
 * @author wangll
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService executorService() {
        int core = Runtime.getRuntime().availableProcessors();
        return new ThreadPoolExecutor(4,
                core * 2,
                0L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(16),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
