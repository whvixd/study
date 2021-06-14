package com.github.whvixd.demo.jdk.reflect;

/**
 * Created by wangzhixiang on 2021/06/14.
 */
public class Test0614 {
    public void testNew(){
        Test061415 t=new Test061415();
        System.out.println(t.getClass().getClassLoader());
        System.out.println(Test0614.class.getClassLoader());
    }
}
