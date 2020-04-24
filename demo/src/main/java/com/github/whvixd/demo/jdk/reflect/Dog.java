package com.github.whvixd.demo.jdk.reflect;

public class Dog implements Animal {



    @Override
    public String type(String type) {
        return "dog";
    }

    @Override
    public String name(String name) {
        return "dahuang";
    }

    @Override
    public Integer age(Integer age) {
        return 5;
    }
}
