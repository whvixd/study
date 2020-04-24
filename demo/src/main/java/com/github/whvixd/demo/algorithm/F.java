package com.github.whvixd.demo.algorithm;

/**
 * 跳台阶问题
 *
 * Created by wangzhx on 2020/4/19.
 */
public class F {
    public int f(int n){
        if(n<=0){
            return 0;
        }
        if(n<=2){
            return n;
        }
        return f(n-1)+f(n-2);
    }
}
