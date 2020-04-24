package com.github.whvixd.demo.jdk.util;

import org.apache.commons.lang.math.NumberUtils;

import java.math.BigDecimal;

/**
 * Created by wangzhx on 2018/6/13.
 */
public class BigDecimalDemo {
    public static void main(String[] args) {
        System.out.println(1.01 + 2.02);//3.0300000000000002，浮点型会失去一定的精度数
        BigDecimal bigDecimal1 = BigDecimal.valueOf(1.01);
        BigDecimal bigDecimal2 = BigDecimal.valueOf(2.02);
        BigDecimal bigDecimal3 = new BigDecimal("3.03");
        BigDecimal bigDecimal5 = new BigDecimal("3.04");
        System.out.println("--"+bigDecimal5.subtract(bigDecimal3).toString());//3.03

        Double d = 1.34;
        if(d instanceof Double){}
        BigDecimal bigDecimal4 = new BigDecimal((double) 1.34);
        String bToS = bigDecimal4.toPlainString();
        System.out.println(bToS);
        NumberUtils.isNumber("1.1");
        System.out.println(BigDecimal.valueOf(1.34E5).toPlainString());//1.34E5 = 1.34*10^5

        double a = 19.01;
        int b = 100;
        System.out.println(a*b);
        System.out.println(Math.round(a*b));
        System.out.println(Math.round(11.5));
    }
}
