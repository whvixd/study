package com.github.whvixd.demo.algorithm.leetcode.easy;

/**
 70. 爬楼梯
 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

 注意：给定 n 是一个正整数。

 示例 1：

 输入： 2
 输出： 2
 解释： 有两种方法可以爬到楼顶。
 1.  1 阶 + 1 阶
 2.  2 阶
 示例 2：

 输入： 3
 输出： 3
 解释： 有三种方法可以爬到楼顶。
 1.  1 阶 + 1 阶 + 1 阶
 2.  1 阶 + 2 阶
 3.  2 阶 + 1 阶

 form https://leetcode-cn.com/problems/climbing-stairs/
 * Created by wangzhx on 2020/6/13.
 */
public enum Q70 {

    instance;

    public int climbStairs(int n) {
        if(n==0)return 0;
        if(n<=2) return n;
        return climbStairs(n-1)+climbStairs(n-2);
    }

    public int climbStairs1(int n) {
        if(n==0)return 0;
        if(n<=2) return n;
        int a=0,b=1,s=0;
        for(int i=1;i<=n;i++){
            s=a+b;
            a=b;
            b=s;
        }
        return s;
    }

    public static void main(String[] args) {
        // 377
        System.out.println(Q70.instance.climbStairs(13));
        System.out.println(Q70.instance.climbStairs1(13));
    }
}
