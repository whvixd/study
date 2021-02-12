package com.github.whvixd.demo.jvm;

/**
 * Created by wangzhixiang on 2021/1/7.
 */
public class RefCountGCDemo {
    public Object instance=null;
    private static final int _1MB=1024*1024;
    private byte[] bigSize=new byte[2*_1MB];
    public static void testGC(){
        RefCountGCDemo r1=new RefCountGCDemo();
        RefCountGCDemo r2=new RefCountGCDemo();
        r1.instance=r2;
        r2.instance=r1;

        r1=null;
        r2=null;

        System.gc();
    }

    public static void main(String[] args) {
        testGC();
    }
}
/*
JVM的GC日志的主要参数包括如下几个：

-verbose:gc

-XX:+PrintGC 输出GC日志

-XX:+PrintGCDetails 输出GC的详细日志

-XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）

-XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）

-XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息

-Xloggc:../logs/gc.log 日志文件的输出路径

 */