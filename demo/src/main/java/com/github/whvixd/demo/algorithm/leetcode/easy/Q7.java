package com.github.whvixd.demo.algorithm.leetcode.easy;

/**
 * 数字反转
 * https://leetcode-cn.com/problems/reverse-integer/
 *
 * Created by wangzhx on 2020/5/19.
 */
public enum Q7 {
    instance;
    public int reverse(int x) {
        long n = 0;
        while(x != 0) {
            n = n*10 + x%10;
            x = x/10;
        }
        return (int)n==n? (int)n:0;
    }

    public static void main(String[] args) {
        System.out.println(Q7.instance.reverse(123));
    }
}
