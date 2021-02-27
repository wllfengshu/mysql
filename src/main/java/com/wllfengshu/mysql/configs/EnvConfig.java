package com.wllfengshu.mysql.configs;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 服务环境的统一配置，服务中使用的环境变量都要在此类中定义
 *
 * @author wangll
 */
@Slf4j
@Data
@Order(0)
@Component
public class EnvConfig implements InitializingBean {

    /**
     * 服务端配置
     */
    @Value("${db_ip:127.0.0.1}")
    private String dbIp;
    @Value("${db_port:3306}")
    private String dbPort;
    @Value("${db_username:root}")
    private String dbUsername;
    @Value("${db_password:root}")
    private String dbPassword;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("程序环境变量:{}", this.toString());
        log.info("系统环境变量:{}", JSON.toJSONString(System.getenv()));
    }
}
