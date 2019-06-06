package com.github.whvixd.demo.annoDemo;

import lombok.experimental.Delegate;

public class Person {

    @Delegate//该类继承了String
    private String name;


    @DoSomething(name = "san")
    public void setName(String name) {
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

}
