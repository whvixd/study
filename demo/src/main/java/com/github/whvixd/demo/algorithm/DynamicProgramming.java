package com.github.whvixd.demo.algorithm;

/**
 * def:
 *      DP(动态规划)，反复的操作过程，但需要用到上一个操作的结果，所以需要将每次操作的结果存储起来，即是动态规划
 * e.g:
 *      挑阶段问题：有n个阶梯，一个人每一步只能跨一个台阶或是两个台阶，问这个人一共有多少种走法？
 *
 * https://www.zhihu.com/question/39948290
 *
 * Created by wangzhixiang on 2020/7/14.
 */
public class DynamicProgramming {
    private static void jump(int n){
        int[] dp=new int[n];

        if(n==1){
            System.out.println(1);
        }if(n==2){
            System.out.println(2);
        }
        int a=0,b=1;
        for(int i=2;i<=n;i++){
            dp[i-1]=a+b;
            a=b;
            b=dp[i-1];
            dp[i-1]=a+b;
        }
        System.out.println(dp[n-1]);
    }

    public static void main(String[] args) {
        jump(10);
    }
}
