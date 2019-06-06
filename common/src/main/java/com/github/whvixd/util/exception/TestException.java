package com.github.whvixd.util.exception;

/**
 * Created by wangzhx on 2018/5/31.
 */
public class TestException extends RuntimeException {
    public TestException() {
    }

    public TestException(String message) {
        super(message);
    }

    public TestException(Throwable cause) {
        super(cause);
    }

    public TestException(String message, Throwable cause) {
        super(message, cause);
    }
}
