package com.github.whvixd.demo.javaDemo.util;

import com.github.whvixd.util.JacksonUtil;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by wangzhx on 2019/7/15.
 */
public class BasicDemo {
    public static void test(String... s){
        for (String k:s){
            System.out.println(k);
        }
    }

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("1","2");
        //3 数组长度，若小于3，输出全部
        test(list.toArray(new String[3]));

        String[] a = new String[]{"1"};
        System.out.println(JacksonUtil.toJson(a));
    }
}
