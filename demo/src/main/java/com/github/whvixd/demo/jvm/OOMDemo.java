package com.github.whvixd.demo.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by wangzhixiang on 2021/1/7.
 */
public class OOMDemo {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        // -Xmx20M -XX:MaxDirectMemorySize=10M
        Field unsafeFiled = Unsafe.class.getDeclaredFields()[0];
        unsafeFiled.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeFiled.get(null);
        while (true) {
            System.out.println("--");
            unsafe.allocateMemory(_1MB);
        }
    }
}
