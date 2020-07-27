package com.github.whvixd.demo.jdk.enumeration;

import lombok.Getter;

/**
 * Created by wangzhixiang on 2020/7/24.
 */
public enum E2 {
    C(3,"C"),D(4,"D");

    @Getter
    private int code;

    @Getter
    private String name;

    E2(int i, String b) {
        this.code=i;
        this.name=b;
    }
}
