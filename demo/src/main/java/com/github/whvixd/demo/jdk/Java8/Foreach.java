package com.github.whvixd.demo.jdk.Java8;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Foreach {

    private void consumerFunc(String k, Object v) {
        if (k == "a") {
            System.out.println(v + "");
        }
    }

    private void test() {

        Map<String, Object> map = Maps.newHashMap();
        map.put("a", "haha");
        map.put("b", "hehe");
        map.put("c", "xixi");

        map.forEach(this::consumerFunc);
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        /*list.forEach(s -> {
            System.out.println(s);
        });
        new Foreach().test();*/


        list.forEach(e -> {
            //会打印下面的信息
            if (e.equals("b")) {
                return;
            }
            System.out.println(e);
        });

        //并行流，默认是串行
        list.parallelStream().forEach(e -> {
            if (e.equals("b")) {
                //并行会打印下面的信息
                throw new RuntimeException();
            }
            System.out.println(e);
        });

        list.forEach(e -> {
            //串行不会打印下面的信息
            if (e.equals("b")) {
                throw new RuntimeException();
            }
            System.out.println(e);
        });


    }

}
