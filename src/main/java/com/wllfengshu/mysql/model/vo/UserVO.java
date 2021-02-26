package com.wllfengshu.mysql.model.vo;

import lombok.Data;

/**
 * 连接该mysql服务器的用户对象
 */
@Data
public class UserVO {

    private String ip;
    private String port;
    private String username;
    private String password;
}
