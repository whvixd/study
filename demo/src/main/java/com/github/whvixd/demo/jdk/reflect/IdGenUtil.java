package com.github.whvixd.demo.jdk.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wangzhixiang on 2021/04/27.
 */
public class IdGenUtil {
    private static Method method;

    public static String genId(){
        try {
            return (String) method.invoke(null,"aaaa");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "";
    }

    static {
        IdGenClassLoader idGenClassLoader=new IdGenClassLoader(IdGenClassLoader.class.getClassLoader());
        try {
            Class<?> idGenCornClass = idGenClassLoader.loadClass("com.github.whvixd.demo.jdk.regex.IdGen");
            method=idGenCornClass.getMethod("genId", String.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();

        }
    }
}
