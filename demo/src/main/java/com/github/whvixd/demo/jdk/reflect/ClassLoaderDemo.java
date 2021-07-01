package com.github.whvixd.demo.jdk.reflect;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 0. 编译 java->class
 * 1. 定位到class文件，链接:校验class是否合法，准备，将符号引用转为直接引用，转为jvm内部的对象
 * 2.
 * 自定义类加载器
 * Created by wangzhx on 2018/3/18 10:27.
 */

@AllArgsConstructor
@NoArgsConstructor
public class ClassLoaderDemo {
    // ClassLoader的无参构造器，会默认将AppClassLoader作为父加载器
    private static ClassLoader classLoader = new ClassLoader() {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
            InputStream in = getClass().getResourceAsStream(fileName);
            Class<?> loadedClass = findLoadedClass(name);
            if(loadedClass!=null){
                return loadedClass;
            }
            if (Objects.isNull(in)) {
                System.out.println(name+" in is null");
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

    public Integer test = 9;

    private static void securityClassLoad(){
        loadCorePackage(classLoader);
    }

    private static void loadCorePackage(ClassLoader loader){
        final String corePackagePath="com.github.whvixd.demo.jdk.reflect.";
        try {
            loader.loadClass(corePackagePath+"Test0614");
            // 先加载，后面new Test061415()时就不用再加载一次了
            loader.loadClass(corePackagePath+"Test061415");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    // 当前类由AppClassLoader加载，而内部Test0614是用自定义的加载器加载，再用反射调用方法，则testNew方法中创建对象会从自定义加载器层级委派
    public static void main(String[] args) throws Exception{
        System.out.println("ParentClassLoader:"+classLoader.getParent().getClass().getSimpleName());
        Thread.currentThread().setContextClassLoader(classLoader);
        ClassLoaderDemo.securityClassLoad();
        Class<?> aClass = classLoader.loadClass("com.github.whvixd.demo.jdk.reflect.Test0614");
        Object test0614 = aClass.getConstructor().newInstance();
        Method testNew = test0614.getClass().getMethod("testNew");
        testNew.invoke(test0614);
        System.out.println(test0614.getClass().getClassLoader());
        System.out.println(ClassLoaderDemo.class.getClassLoader());
//        Class<?> name = Class.forName("com.github.whvixd.demo.javaDemo.thread.ClassLoaderDemo");
//        Constructor<?> declaredConstructor = name.getDeclaredConstructor(Integer.class);
//        Object o = declaredConstructor.newInstance(2);
    }
}
