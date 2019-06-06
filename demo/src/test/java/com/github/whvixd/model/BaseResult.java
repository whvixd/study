package com.github.whvixd.model;

import lombok.Data;

/**
 * Created by wangzhx on 2018/10/29.
 */
@Data
public class BaseResult<T> {
    protected String errCode;
    protected String errMessage;
    protected T result;


    public boolean isSuccess() {
        return this.errCode.equals("0");
    }
}
