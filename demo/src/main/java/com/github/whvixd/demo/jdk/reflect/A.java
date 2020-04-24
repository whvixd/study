package com.github.whvixd.demo.jdk.reflect;

import java.lang.reflect.ParameterizedType;

/**
 * Created by wangzhx on 2019/1/12.
 */
public class A<T, E> {
    void test() throws ClassNotFoundException {
        //ParameterizedType 转为范型
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        //拿到E 对应的Class
        Class<?> clazz = Class.forName(parameterizedType.getActualTypeArguments()[1].getTypeName());
        System.out.println(clazz.getSimpleName());
    }

}
