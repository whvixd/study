package com.github.whvixd.demo.spring;

public class Bean {
    String name;

    public String getName() {
        return "hello";
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                '}';
    }
}