package com.github.whvixd.demo.javaDemo.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * Created by wangzhx on 2019/7/25.
 */
public class ComparatorUtilDemo {
    private static void comparatorList() {
        List<String> list = Lists.newArrayList("3", "1", "a");
        ArrayList<Object> collect = list.stream().sorted((o1, o2) -> {
            if ("a".equals(o1) ) {
                return -1;
            } else if ("1".equals(o1)) {
                return -1;
            }else if("3".equals(o1)){
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

        List<Map.Entry<String,String>> list = new ArrayList<>(map.entrySet());
        //升序排序
        list.sort(Comparator.comparing(Map.Entry::getValue));

        for(Map.Entry<String,String> mapping:list){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
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


    public static void main(String[] args) {
        comparatorTreeMap();
    }
}
