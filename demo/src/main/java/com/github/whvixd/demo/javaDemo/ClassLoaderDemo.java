package com.github.whvixd.demo.javaDemo;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * Created by wangzhx on 2018/3/18 10:27.
 */


@AllArgsConstructor
public class ClassLoaderDemo {

    public Integer test = 9;
    public static Class<? extends Object> get(String name) throws ClassNotFoundException {
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

    public static void main(String[] args) throws ClassNotFoundException, IOException, NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String name = "ClassLoaderDemo";
        Class clazz = get(name);
    }
}
