package com.github.whvixd.demo.algorithm;

/**
 * 跳台阶问题
 *
 * Created by wangzhx on 2020/4/19.
 */
public class Fibonacci {
    public int invoke(int n){
        if(n<=0){
            return 0;
        }
        if(n<=2){
            return n;
        }
        return invoke(n-1)+invoke(n-2);
    }
}
