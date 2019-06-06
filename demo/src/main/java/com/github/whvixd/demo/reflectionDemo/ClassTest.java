package com.github.whvixd.demo.reflectionDemo;

import java.lang.reflect.ParameterizedType;

/**
 * Created by wangzhx on 2019/3/14.
 */
public class ClassTest extends A<Dog,Demo>{


    public static void main(String[] args) throws ClassNotFoundException {
        new ClassTest().test();
    }
}
