package com.github.whvixd.demo.javaDemo.serviceloader.impl;

import com.github.whvixd.demo.javaDemo.serviceloader.AnimalDemo;

/**
 * Created by wangzhx on 2019/5/24.
 */
public class DogDemo implements AnimalDemo {
    @Override
    public void say() {
        System.out.println("it is dog");
    }
}
