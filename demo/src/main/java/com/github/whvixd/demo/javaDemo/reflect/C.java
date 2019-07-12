package com.github.whvixd.demo.javaDemo.reflect;

/**
 * Created by wangzhx on 2019/1/12.
 */
public class C {

    public void test(Class<? extends A> aClazz) throws IllegalAccessException, InstantiationException {
        A a = aClazz.newInstance();
//        a.print();

    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        C c = new C();
        c.test(AImpl.class);
    }
}
