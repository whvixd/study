package com.github.whvixd.demo.javaDemo;

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
        System.out.println(bigDecimal1.add(bigDecimal2).toString());//3.03

        Double d = 1.34;
        if(d instanceof Double){}
        BigDecimal bigDecimal4 = new BigDecimal((double) 1.34);
        String bToS = bigDecimal4.toPlainString();
        System.out.println(bToS);

        System.out.println(BigDecimal.valueOf(1.34E5).toPlainString());//1.34E5 = 1.34*10^5
    }
}
