package com.github.whvixd.demo.jdk.util;

import java.lang.reflect.Modifier;
import java.util.LinkedList;

/**
 * clazz1.isAssignableFrom(clazz2)
 * 判断左侧是否与右侧的clazz相等，或是它的子类
 */
public class IsAssignableFrom {
    public static void main(String[] args) {
        String a = new String();
        String b = new String();
        boolean boo = a.getClass().isAssignableFrom(b.getClass());

        System.out.println(boo);

        Object o = new Object();
        LinkedList linkedList = new LinkedList();
        System.out.println(o.getClass().isAssignableFrom(linkedList.getClass()));

        System.out.println("被public修饰："+Modifier.isPublic(IsAssignableFrom.class.getModifiers()));

    }
}
