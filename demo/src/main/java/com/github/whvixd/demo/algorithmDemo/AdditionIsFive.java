package com.github.whvixd.demo.algorithmDemo;

import java.util.stream.IntStream;

/**
 * Created by wangzhx on 2019/6/25.
 */
public class AdditionIsFive {
    private static void print() {
        IntStream.range(100, 1000).forEach(number -> {
            int i = number / 100;//百位数
            int j = number % 10 / 10;//十位数
            int k = number % 100;//个位数
            if (i + j + k == 5) {
                System.out.println(number);
            }
        });
    }

    public static void main(String[] args) {
        print();
    }
}
