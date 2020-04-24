package com.github.whvixd.demo.guava;

import com.google.common.collect.HashMultimap;

/**
 * Created by wangzhx on 2018/4/23.
 */
public class HashMultiMapDemo {
    public static void main(String[] args) {
        HashMultimap<Integer,Object> hashMultimap = HashMultimap.create();
        hashMultimap.put(1,"q");
        hashMultimap.put(1,"w");
        hashMultimap.put(1,"e");
        hashMultimap.put(2,"qq");
        hashMultimap.put(2,"ww");
        for(int p:hashMultimap.keySet()){
            System.out.println(p+"\t"+hashMultimap.get(p));
        }
    }
}
