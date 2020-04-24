package com.github.whvixd.util.datastructure;

import org.junit.Test;

/**
 * Created by wangzhx on 2020/4/23.
 */
public class LRUTest {
    @Test
    public void testSet(){
        LRU<Integer,Integer> lru = new LRU<>(3);
        lru.set(1,1);
        lru.set(2,2);
        lru.set(3,3);


        lru.print();
        System.out.println();
        lru.get(1);
        lru.print();
        System.out.println();
        // 去掉2
        lru.set(4,4);
        lru.print();
    }
}
