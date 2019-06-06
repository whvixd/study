package com.github.whvixd.demo.Java8;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;

/**
 * BinaryOperator<T> 输入两个<T> t ,返回一个<T>值
 */
public class BinaryOperatorDemo {
    public static void main(String[] args) {
        BinaryOperator<Integer> binaryOperator = (x, y) -> {
            int z = x + y;
            return z;
        };
        BinaryOperator.maxBy((x, y) -> (int) x > (int) y ? 1 : 0).apply(1, 2);

        int z = binaryOperator.apply(1, 2);
        System.out.println(z);

        DoubleBinaryOperator doubleBinaryOperator = (x, y) -> x + y;
        doubleBinaryOperator.applyAsDouble(1.0, 2.0);

        FunctionT functionT = (Integer a, Integer b) -> {
            return a + b;
        };

    }

    @FunctionalInterface
    interface FunctionT {
        Integer name(Integer a, Integer b);
    }
}
