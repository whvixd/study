package com.github.whvixd.demo.springDemo;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangzhx on 2019/7/9.
 */

public class Service {
    @Autowired
    private AsyncDemo asyncDemo;

    public void test(){
        asyncDemo.asyncTest();
        System.out.println("Service.test");
    }
}
