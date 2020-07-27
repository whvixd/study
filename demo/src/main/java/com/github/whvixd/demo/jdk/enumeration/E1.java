package com.github.whvixd.demo.jdk.enumeration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by wangzhixiang on 2020/7/24.
 */
public enum  E1 {
    A(1,"A_Name"),B(2,"B");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String name;

    E1(int i, String b) {
        this.code=i;
        this.name=b;
    }

}
