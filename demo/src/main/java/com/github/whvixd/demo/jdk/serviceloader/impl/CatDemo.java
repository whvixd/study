package com.github.whvixd.demo.jdk.serviceloader.impl;

import com.github.whvixd.demo.jdk.serviceloader.AnimalDemo;

/**
 * Created by wangzhx on 2019/5/24.
 */
public class CatDemo implements AnimalDemo {
    @Override
    public void say() {
        System.out.println("it is cat");
    }
}
