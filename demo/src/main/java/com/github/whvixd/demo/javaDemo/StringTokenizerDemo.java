package com.github.whvixd.demo.javaDemo;

import java.util.StringTokenizer;

/**
 * Created by wangzhx on 2018/4/14.
 */
public class StringTokenizerDemo {


    public static void main(String[] args) {
        StringTokenizer stringTokenizer = new StringTokenizer("-1,ab,c,,1",",");
//        System.out.println(stringTokenizer.countTokens());
//        System.out.println(stringTokenizer.nextToken());
//        System.out.println(stringTokenizer.nextToken("-1"));

        while (stringTokenizer.hasMoreElements()){
            System.out.println(stringTokenizer.nextToken());
        }
    }
}