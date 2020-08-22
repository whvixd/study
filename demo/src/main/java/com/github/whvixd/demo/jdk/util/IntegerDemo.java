package com.github.whvixd.demo.jdk.util;

/**
 * Created by wangzhixiang on 2020/8/22.
 */
public class IntegerDemo {

    public static void main(String[] args) {
        Integer i1 = 127;// 默认调用 Integer.valueOf();
        Integer i2 = 127;

        Integer i3 = 1024;
        Integer i4 = 1024;

        int i5 = i1;// 默认调用i1.intValue();

        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
    }
}
