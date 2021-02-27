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
        ILLEGAL_PARAM(400),
        // 选择数据库时，必须使用use关键字
        MUST_USE_DB(1000),
        // 读取文件失败
        FAIL_READ_FILE(1001),
        // 读取路径下的所有文件夹名失败
        FAIL_READ_DIR_FOR_DIR_NAME(1002),
        // 读取路径下的所有文件夹失败
        FAIL_READ_DIR_FOR_FILE_NAME(1003),
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
