package com.github.whvixd.demo.jdk.Java8;

import java.util.function.Predicate;

/**
 * Predicate<T> 函数输入一个 T 类型的参数 ，
 * 返回Boolean类型
 */
public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<String> predicate = (x) -> {
            boolean flag = x.equals("test");
            return flag;
        };
        System.out.println(predicate.test("test"));

        Predicate predicate1 = predicate.negate();//negate() 方法 是原Predicate的反面(true -> false)
        System.out.println(predicate1.test("test"));

        predicate.or(predicate1);//  ||
        predicate.and(predicate1);//  &&

    }
}
