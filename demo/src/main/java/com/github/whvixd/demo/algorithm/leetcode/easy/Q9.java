package com.github.whvixd.demo.algorithm.leetcode.easy;

import java.util.Stack;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * https://leetcode-cn.com/problems/palindrome-number/
 *
 * Created by wangzhx on 2020/5/19.
 */
public enum Q9 {
    instance;
    public boolean isPalindrome(int x) {
        Stack<Integer> stack = new Stack<>();
        if(x>=0) {
            int t = x;
            while (t > 0) {
                // 入栈
                stack.push(t % 10);
                t = t / 10;
            }

            while (!stack.empty()) {
                // 出栈 对比数字
                if (stack.pop() != x % 10) {
                    return false;
                }
                x = x / 10;
            }
            return true;
        }
        return false;
    }


    public boolean isPalindrome2(int x) {
        if(x<0)return false;
        int t=x;
        int digit=1;
        // 获取 位数 121 -> 100
        while (t>=10){
            t/=10;
            digit=digit*10;
        }

        // 最大位数字
        int l=x;
        // 最小位数字
        int r=x;
        // 对比最大小数字，是否相等
        while (digit>0){
            if(!(r%10==l/digit)){
                return false;
            }
            l=l-l/digit*digit;
            digit=digit/10;
            r=r/10;
        }
        return true;
    }

    public static void main(String[] args) {
        // assert true
        System.out.println(Q9.instance.isPalindrome(121121));
        // assert true
        System.out.println(Q9.instance.isPalindrome(0));
        // assert true
        System.out.println(Q9.instance.isPalindrome(123321));
        // assert true
        System.out.println(Q9.instance.isPalindrome(12321));
        // assert false
        System.out.println(Q9.instance.isPalindrome(10));
        // assert true
        System.out.println(Q9.instance.isPalindrome(101));

        // assert true
        System.out.println(Q9.instance.isPalindrome2(121121));
        // assert true
        System.out.println(Q9.instance.isPalindrome2(0));
        // assert true
        System.out.println(Q9.instance.isPalindrome2(123321));
        // assert true
        System.out.println(Q9.instance.isPalindrome2(12321));
        // assert false
        System.out.println(Q9.instance.isPalindrome2(10));
        // assert true
        System.out.println(Q9.instance.isPalindrome2(101));
        // assert true
        System.out.println(Q9.instance.isPalindrome2(101101));
        // assert false
        System.out.println(Q9.instance.isPalindrome2(-101101));
    }
}
