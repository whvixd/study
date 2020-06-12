package com.github.whvixd.demo.jdk.util;

import com.github.whvixd.util.bean.BeanUtil;
import lombok.Data;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public class IntrospectorDemo {

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Bean bean = new Bean();
        bean.id=1;
        bean.name="test";
        A a = new A();
        a.name="a";
        bean.a=a;
        System.out.println(BeanUtil.toMap(bean));

        B b=new B();
        // todo 添加到博客中
        BeanUtil.copyProperty(a,b);
        System.out.println(b);
    }

    public static class Bean extends Base {
        int id;
        String name;
        A a;

        public int getId(){
            return this.id;
        }

        public String getName(){
            return this.name;
        }
        public A getA(){
            return this.a;
        }
    }

    public static class Base{}

    @Data
    public static class A{
        private String name;
    }

    @Data
    public static class B{
        private String name;
    }




}
