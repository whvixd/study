package com.github.whvixd.demo.jdk.jni;

/**
 * 参考 https://blog.csdn.net/u010853261/article/details/53470514
 * 包下有问题
 *
 * Created by wangzhx on 2020/5/18.
 */
public class HelloNative {

    static
    {
        System.setProperty("java.library.path", ".");
        System.loadLibrary("hello");
    }

    private native void hello();

    public static void main(String[] args) {
        HelloNative helloNative = new HelloNative();
        helloNative.hello();
    }
}
