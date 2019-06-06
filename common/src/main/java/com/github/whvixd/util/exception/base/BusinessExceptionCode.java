package com.github.whvixd.util.exception.base;

/**
 * 异常信息
 * Created by wangzhx on 2018/8/12.
 */
public enum BusinessExceptionCode {
    ARG_VALIDATE_ERROR(5_01, "参数校验错误");

    private int errorCode;
    private String errorMessage;

    BusinessExceptionCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
