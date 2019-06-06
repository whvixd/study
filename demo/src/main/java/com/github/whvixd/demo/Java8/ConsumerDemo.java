package com.github.whvixd.demo.Java8;

import java.util.function.Consumer;

/**
 * Consumer<T>
 * 提供一个输入参数<T>，进行操作，无返回值
 */
public class ConsumerDemo {

    public static void main(String[] args) {
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("hello");
    }
}
