package com.github.whvixd.demo.javaDemo.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Created by wangzhx on 2019/7/9.
 */
public class AtomicIntegerDemo {
    public static void insert() {
        AtomicInteger count = new AtomicInteger();
        IntStream.range(0, 100).forEach(k -> {
            int i = count.incrementAndGet();
            System.out.println(i);

        });
    }

    public static void main(String[] args) {
        insert();
    }
}
