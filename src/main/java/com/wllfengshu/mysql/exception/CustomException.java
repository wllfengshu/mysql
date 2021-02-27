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
        // 无法找到文件或目录
        NOT_FOUND_FILE_OR_DIR(1002),
        // insert失败
        FAIL_INSERT(2001),
        // delete失败
        FAIL_DELETE(3001),
        // update失败
        FAIL_UPDATE(4001),
        // select失败
        FAIL_SELECT(5001),
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
