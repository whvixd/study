package com.github.whvixd.demo.jdk.Java8;

import java.util.function.Supplier;

/**
 * Supplier<T> 只有返回值，没有输入值
 */
public class SupplierDemo {

    public static void main(String[] args) {
        int i = 1;
        Supplier<Integer> supplier = () -> i + 1;
        int outI = supplier.get();
        System.out.println(outI);
    }
}
