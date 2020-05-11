package com.github.whvixd.demo.jdk.util;

/**
 * Created by wangzhx on 2020/5/8.
 */
public class GotoDemo {
    public static void main(String[] args) {
        int i=1;
        retry:
        for(;;){
            i++;
            System.out.println(i);
            if(i==10){
                break retry;
            }
        }
    }
}
