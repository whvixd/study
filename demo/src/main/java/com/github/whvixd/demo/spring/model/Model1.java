package com.github.whvixd.demo.spring.model;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangzhx on 2020/3/10.
 */
//@ToString
public class Model1 {
    @Setter
    private String name="Model1";

    @Autowired
    @Getter
    private AsyncDemo asyncDemo;

    @Override
    public String toString() {
        return "Model1{" +
                "name='" + name + '\'' +
                ", asyncDemo.name=" + asyncDemo.getName() +
                '}';
    }
    public void round(ProceedingJoinPoint proceedingJoinPoint){
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("aop round");
    }
}
