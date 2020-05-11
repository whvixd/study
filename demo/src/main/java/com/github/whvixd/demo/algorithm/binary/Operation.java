package com.github.whvixd.demo.algorithm.binary;

/**
 * Created by wangzhx on 2020/5/11.
 */
public class Operation {

    public static void main(String[] args) {
        System.out.println(2/* x */ << 3/* y */);//左移:正数 2*(2^3)=16，规则:x*2^y
        System.out.println(16/* x */>>3/* y */);//右移:正数 16/(2^3)=2，规则:x/2^y
    }
}
