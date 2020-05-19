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
                stack.push(t % 10);
                t = t / 10;
            }

            while (!stack.empty()) {
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
        if(x<0){
            return false;
        }
        int num=1;
        int t=x;
        while (t>=10){
            t/=10;
            num=num*10;
        }

        int a=x;
        int b=x;
        while (num>0){
            if(!(a%10==b/num)){
                return false;
            }
            b=b-b/num*num;
            num=num/10;

            a=a/10;
        }
        return true;

    }

    public static void main(String[] args) {
        System.out.println(Q9.instance.isPalindrome(121121));
        System.out.println(Q9.instance.isPalindrome(0));
        System.out.println(Q9.instance.isPalindrome(123321));
        System.out.println(Q9.instance.isPalindrome(12321));
        System.out.println(Q9.instance.isPalindrome(10));
        System.out.println(Q9.instance.isPalindrome(101));

        System.out.println("-----------------");
        System.out.println(Q9.instance.isPalindrome2(121121));
        System.out.println(Q9.instance.isPalindrome2(0));
        System.out.println(Q9.instance.isPalindrome2(123321));
        System.out.println(Q9.instance.isPalindrome2(12321));
        System.out.println(Q9.instance.isPalindrome2(10));
        System.out.println(Q9.instance.isPalindrome2(101));
        System.out.println(Q9.instance.isPalindrome2(101101));
    }
}
