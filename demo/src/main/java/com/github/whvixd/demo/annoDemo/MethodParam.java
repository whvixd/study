package com.github.whvixd.demo.annoDemo;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Method;

public class MethodParam {
    public static void main(String[] args) {

        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();

        Demo demo = new Demo();
        Method[] methods = demo.getClass().getDeclaredMethods();

        String[] params = u.getParameterNames(methods[0]);
        for (String param:params){
            System.out.println(param);
        }


    }
    static class Demo{
        public void add(int a,int b){
            System.out.println(a + b);
        }
    }
}
