package com.github.whvixd.demo.javaDemo.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wangzhx on 2019/7/25.
 */
public class ComparatorUtilDemo {
    private static void comparatorList() {
        List<String> list = Lists.newArrayList("3", "1", "a");
        ArrayList<Object> collect = list.stream().sorted((o1, o2) -> {
            if ("a".equals(o1)) {
                return -1;
            } else if ("1".equals(o1)) {
                return -1;
            } else if ("3".equals(o1)) {
                return -1;
            }
            return 0;
        }).collect(Lists::newArrayList, ArrayList::add, (k, v) -> {
        });
        System.out.println(collect);
    }

    private static void comparatorMap() {
        Map<String, String> map = new HashMap<>();
        map.put("c", "ccccc");
        map.put("a", "aaaaa");
        map.put("b", "bbbbb");
        map.put("d", "ddddd");

        List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
        //升序排序
        list.sort(Comparator.comparing(Map.Entry::getValue));

        for (Map.Entry<String, String> mapping : list) {
            System.out.println(mapping.getKey() + ":" + mapping.getValue());
        }
    }

    private static void comparatorTreeMap() {
        List<String> list = Lists.newArrayList("3", "1", "a");
        Map<String, String> collect = list.stream().collect(() -> Maps.newTreeMap((o1, o2) -> {
            if ("a".equals(o1)) {
                return -1;
            } else if ("1".equals(o1)) {
                return -1;
            } else if ("3".equals(o1)) {
                return -1;
            }
            return 0;
        }), (m, k) -> m.put(k, k), Map::putAll);
        System.out.println(collect);
    }

    private static void comparatorAs(){
        A a1 = new A("1", "m", 18);
        A a2 = new A("2", "m", 21);
        A a4 = new A("4", "w", 20);
        A a3 = new A("3", "m", 19);

        List<A> as = Lists.newArrayList(a1, a2, a3, a4);
        System.out.println("before:" + as);

        as = as.stream().sorted((o1, o2) -> {
            if ("m".equals(o1.getSex()) && !"m".equals(o2.getSex())) {
                return -1;
            } else {
                if (o1.getAge() > o2.getAge()) {
                    return -1;
                }
            }

            return 0;
        }).collect(Collectors.toList());
        System.out.println("after:" + as);
    }


    public static void main(String[] args) {
//        comparatorAs();
        List<String> a = Lists.newArrayList("1","3");
        List<String> b = Lists.newArrayList("2","4");
        a.addAll(a.size(),b);
        System.out.println(a);

    }


    @Data
    @AllArgsConstructor
    static class A {
        private String name;
        private String sex;
        private int age;
    }
}
