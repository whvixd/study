package com.github.whvixd.demo.jdk.reflect;

import lombok.ToString;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * Created by wangzhx on 2018/3/18 14:11.
 */

@ToString
public class ClassDemo {
    private String id;
    private String name;
    private Integer age;

    private ClassDemo(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static <T> T getInstance(Object... objects) {
        Class<T> tClass = (Class<T>) ClassDemo.class;
        Field[] fields = tClass.getDeclaredFields();
        Class[] clazzs = new Class[fields.length];
        if (objects.length != fields.length) {
            throw new IllegalArgumentException("参数个数不匹配！");
        }
        for (int i = 0; i < fields.length; i++) {
            clazzs[i] = fields[i].getType();
            if (!objects[i].getClass().isAssignableFrom(fields[i].getType())) {
                throw new ClassCastException("类型不匹配！");
            }
        }
        T t = null;
        try {
            t = tClass.getDeclaredConstructor(clazzs).newInstance(objects);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (Objects.isNull(t)) {
            throw new NullPointerException();
        }
        return t;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ClassDemo classDemo = getInstance(new Object[]{"123", "张三", 21});
        String name = (String) ClassDemo.class.getDeclaredField("name").get(classDemo);
        System.out.println("name:" + name);
        System.out.println(classDemo.toString());
    }
}
