package com.github.whvixd.demo.javaDemo.util;

import com.google.common.math.IntMath;

import java.text.DecimalFormat;

/**
 * Created by wangzhx on 2018/3/23 10:17.
 */
public class FormatDemo {


    public static String formatToString(Object o){
        DecimalFormat decimalFormat = new DecimalFormat("#####.####");
        return decimalFormat.format(o);
    }

    public static void main(String[] args) {

        System.out.println(formatToString(313));

        System.out.println(IntMath.checkedPow(2,3));
    }
}
