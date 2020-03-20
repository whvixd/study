package com.github.whvixd.demo.springDemo;

import com.sun.org.apache.xpath.internal.operations.Number;
import org.apache.commons.lang.math.NumberUtils;
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
