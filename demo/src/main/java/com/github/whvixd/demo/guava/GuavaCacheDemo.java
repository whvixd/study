package com.github.whvixd.demo.guava;

import com.github.whvixd.demo.Entity;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Ordering;
import lombok.Data;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * GuavaCache Demo
 * <p>
 * Created by wangzhx on 2018/6/1.
 */
public class GuavaCacheDemo {

    LoadingCache<Object, Entity.Student> guavaCache = CacheBuilder.newBuilder()

            //expireAfterAccess(long, TimeUnit) 这个方法是根据某个键值对最后一次访问之后多少时间后移除，若一直读，就不会失效
            .expireAfterAccess(30, TimeUnit.SECONDS)
            //expireAfterWrite(long, TimeUnit) 这个方法是根据某个键值对被创建或值被替换后多少时间移除，若key唯一，在一定时间内后失效
            //.expireAfterWrite(1, TimeUnit.SECONDS)
            .initialCapacity(100)
            //CacheBuilder.recordStats()用来开启Guava Cache的统计功能。统计打开后，Cache.stats()方法会返回CacheStats对象以提供一些统计信息。具体信息可以查看CacheStats类的定义。
            .recordStats().build(
                    new CacheLoader<Object, Entity.Student>() {
                        @Override
                        public Entity.Student load(Object key) throws Exception {
                            if (key instanceof String) {
                                return new Entity.Student(1, (String) key, new Entity.Course("数学", 99.00));
                            } else if (key instanceof CacheKey) {
                                return new Entity.Student(1, ((CacheKey) key).getName(),
                                        new Entity.Course(((CacheKey) key).getNode().getName(), 99.00));
                            }
                            //null，直接异常
                            return null;
                        }
                    }
            );

    public static void main(String[] args) throws ExecutionException {
        /*
         1.单独移除用 Cache.invalidate(key)
         2.批量移除用 Cache.invalidateAll(keys)
         3.移除所有用 Cache.invalidateAll()
         基于引用回收：
         1. CacheBuilder.weakKeys()：使用弱引用存储键。当键没有其它（强或软）引用时，缓存项可以被GC回收
         2. CacheBuilder.weakValues()：使用弱引用存储值。当值没有其它（强或软）引用时，缓存项可以被GC回收
         3. CacheBuilder.softValues()：使用软引用存储值。软引用只有在响应内存需要时，才按照全局最近最少使用的顺序回收。影响性能，

        */
        GuavaCacheDemo guavaCacheDemo = new GuavaCacheDemo();
        CacheKey cacheKey1 = new CacheKey();
        Node node1 = new Node();
        node1.setName("math");
        cacheKey1.setNode(node1);
        cacheKey1.setName("1");

        CacheKey cacheKey2 = new CacheKey();
        Node node2 = new Node();
        node2.setName("chinese");
        cacheKey2.setNode(node2);
        cacheKey2.setName("1");

        System.out.println(cacheKey1.equals(cacheKey2));

//        Map<String, Object> map = guavaCacheDemo.guavaCache.get("张三");
        Entity.Student student1 = guavaCacheDemo.guavaCache.get(cacheKey1);
        Entity.Student student2 = guavaCacheDemo.guavaCache.get(cacheKey2);

        CacheStats cacheStats = guavaCacheDemo.guavaCache.stats();
        System.out.println(cacheStats.toString());

        System.out.println(student1);
        System.out.println(student2);
        Ordering ordering = Ordering.arbitrary();

    }

    @Data
    static class CacheKey {
        private Node node;
        private String name;

        @Override
        //单独去比较两个对象是否相同
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CacheKey cacheKey = (CacheKey) o;
            return name != null ? name.equals(cacheKey.name) : cacheKey.name == null;
        }

        @Override
        //HashMap k是用hashCode()去存放位置
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }

    @Data
    static class Node {
        int no;
        String name;
    }
}
