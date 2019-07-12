package com.github.whvixd.demo.javaDemo.Java8;

import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        Function<Integer, String> function = (x) -> {
            System.out.print(x);
            return "f";
        };
        function.apply(1);
        function.andThen((x) -> {
            System.out.println(x);
            return "a";
        });
        function.apply(3);

        Function<Integer, Integer> function1 = e -> e * 2;//Function<A,B> 这个A 是输入的类型 ，B是返回的类型
        Function<Integer, Integer> function2 = e -> e - 10;
        Function<Integer, Integer> function3 = e -> e - 9;

        //----------------------------------------------------
        Function<Integer, String> function4 = Object::toString;
        Function<Integer, String> function5 = e -> e.toString();
        //----------------------------------------------------

        System.out.println("compose方法demo  :" + function2.compose(function1).compose(function3).apply(2));//把 计算function1的返回值 作为function2的参数(右到左)入参
        System.out.println(function2.andThen(function1).andThen(function3).apply(2));// 与compose相反（左到右）入参
    }
}
