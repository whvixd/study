package com.github.whvixd.demo.algorithmDemo;

/**
 * Created by wangzhx on 2019/10/18.
 */
public class Sum {
    static int sum(int n) {
        int result;
        if (n < 0) {
            throw new RuntimeException();
        } else if (n == 1) {
            return 1;
        } else {
            result = n;
            result += sum(n - 1);
            return result;
        }

    }

    public static void main(String[] args) {
        System.out.println(sum(2));
    }
}
