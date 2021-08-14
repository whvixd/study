package com.github.whvixd.demo.jdk.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 敏感数据 -> 算法 -> 加密的id
 * Created by wangzhixiang on 2021/04/27.
 */
public class IdGenUtil {
    private static Method method;

    public static String genId(String id){
        try {
            return (String) method.invoke(null,"aaaa");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return "";
    }

    static {
        IdGenClassLoader idGenClassLoader=new IdGenClassLoader(IdGenClassLoader.class.getClassLoader());
        try {
            Class<?> idGenCornClass = idGenClassLoader.loadClass("com.github.whvixd.demo.jdk.regex.IdGen");
            method=idGenCornClass.getMethod("genId", String.class);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
