package com.wllfengshu.mysql.exception;

/**
 * 自定义异常
 */
public class CustomException extends Exception {

    protected final ExceptionName exceptionName;

    public enum ExceptionName {
        // 没有权限
        UNAUTHENTICATED(401),
        // 非法参数
        ILLEGAL_PARAM(400)
        ;

        private int code;

        ExceptionName(int code) {
            this.code = code;
        }
    }

    public CustomException(String message, ExceptionName exceptionName) {
        super(message);
        this.exceptionName = exceptionName;
    }
}
