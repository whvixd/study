package com.github.whvixd.demo.algorithm;

/**
 * 跳台阶问题
 *
 * Created by wangzhx on 2020/4/19.
 */
public class Fibonacci {
    public int function1(int n){
        if(n<=0){
            return 0;
        }
        if(n<=2){
            return n;
        }
        return function1(n-1)+function1(n-2);
    }

    public int function2(int n){
        if(n<=0){
            return 0;
        }
        if(n==1||n==2){
            return n;
        }
        int a=0,b=1;
        int target=0;
        for(int i=2;i<=n;i++){
            target=a+b;
            a=b;
            b=target;
        }
        return target;
    }
}
