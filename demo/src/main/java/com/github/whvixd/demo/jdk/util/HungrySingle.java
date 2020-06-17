package com.github.whvixd.demo.jdk.util;

/**
 * 饿汉式
 */
public class HungrySingle {

    private HungrySingle hungrySingle=new HungrySingle();

    private HungrySingle(){}

    public HungrySingle getInstance(){
        return this.hungrySingle;
    }

}
