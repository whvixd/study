package com.github.whvixd.junit4;


import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Created by wangzhx on 2020/4/16.
 */
public class TestDependsOnMethod {
    int id;

    @BeforeClass
    public void setup() {
        System.out.println("setup");
    }

    @AfterClass
    public void shutdown() {
        id = 2;
        System.out.println("shutdown");
    }

    public void login(){
        System.out.println("login");
    }

}
