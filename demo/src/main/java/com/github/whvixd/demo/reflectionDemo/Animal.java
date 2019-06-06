package com.github.whvixd.demo.reflectionDemo;

public interface Animal {
    String type(String type);

    String name(String name);

    Integer age(Integer age);

    default void print(String name){
        System.out.println(name + "is invoked");
    }

}
