package com.github.whvixd.demo.dataStructure;

/**
 * Created by wangzhx on 2019/9/16.
 */
public class LNode {

    public static void main(String[] args) {
        int x = 2, y = 4, z = 6;
        if (x > y)
            z = x;
        x = y;
        y = z;
        System.out.println(x + "," + y + "," + z);
    }
}
