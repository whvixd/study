package com.github.whvixd.demo.javaDemo;

/**
 * Created by wangzhx on 2018/6/7.
 *
 * 每个java程序都有JVM，可以通过Runtime.getRuntime()来获取JVM各个参数
 */
public class RuntimeDemo {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.freeMemory());//获取JVM空余内存
        System.out.println(runtime.availableProcessors());//获取JVM核数
    }
}
