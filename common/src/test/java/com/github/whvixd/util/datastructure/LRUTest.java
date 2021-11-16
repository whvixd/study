package com.github.whvixd.util.datastructure;

import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Created by wangzhx on 2020/4/23.
 */
public class LRUTest {
    @Test
    public void testSet(){
        LRU lru = new LRU(3);
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


    @Test
    public void test(){
        int size=100000;
        // 初始化一万的容量，并添加
        LRU lruCache = new LRU(size);
        long putStart = System.currentTimeMillis();
        IntStream.range(0,size).forEach(i->{
            lruCache.set(i,i);
        });
        long putEnd = System.currentTimeMillis();

        long getStart = System.currentTimeMillis();
        IntStream.range(0,size).forEach(i->{
            lruCache.get(RandomUtil.randomInt(size));
        });
        long getEnd = System.currentTimeMillis();

        System.out.println("put:"+(putEnd-putStart)+"ms");
        System.out.println("get:"+(getEnd-getStart)+"ms");
    }
}
