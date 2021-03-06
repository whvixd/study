package com.github.whvixd.demo.jdk.reflect;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * 0. 编译 java->class
 * 1. 定位到class文件，链接:校验class是否合法，准备，将符号引用转为直接引用，转为jvm内部的对象
 * 2.
 * Created by wangzhx on 2018/3/18 10:27.
 */
@AllArgsConstructor
public class ClassLoaderDemo {

    public Integer test = 9;
    public static Class<?> getClass(String name) throws ClassNotFoundException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream in = getClass().getResourceAsStream(fileName);
                if (Objects.isNull(in)) {
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[in.available()];//来判断流的长度,在没有执行read()操作，调用read()之后就为0
                    in.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            }
        };
        return classLoader.loadClass(name);
    }

    public static void main(String[] args) throws Exception{
        String name = "com.github.whvixd.demo.javaDemo.thread.ClassLoaderDemo";
        Class clazz = getClass(name);
        System.out.println();
//        Class<?> name = Class.forName("com.github.whvixd.demo.javaDemo.thread.ClassLoaderDemo");
//        Constructor<?> declaredConstructor = name.getDeclaredConstructor(Integer.class);
//        Object o = declaredConstructor.newInstance(2);
    }
}
