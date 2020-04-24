package com.github.whvixd.demo.spring;

import org.springframework.beans.factory.InitializingBean;

public class TestInitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {


        System.out.println("---afterPropertiesSet---");
    }

    public void init(){
        System.out.println("---init---");
    }
}
