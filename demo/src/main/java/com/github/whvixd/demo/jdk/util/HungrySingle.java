package com.github.whvixd.demo.jdk.util;

/**
 * 饿汉式
 */
public class HungrySingle {

    private final static HungrySingle INSTANCE=new HungrySingle();

    private HungrySingle(){}

    public static HungrySingle getInstance(){
        return INSTANCE;
    }

}
