package com.github.whvixd.demo.spring;

import com.github.whvixd.demo.spring.model.AsyncDemo;
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
