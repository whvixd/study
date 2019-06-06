package com.github.whvixd.demo.GuavaDemo;

import com.github.whvixd.demo.Entity;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * GuavaCache Demo
 *
 * Created by wangzhx on 2018/6/1.
 */
public class GuavaCacheDemo {

    LoadingCache<Object, Map<String, Object>> guavaCache = CacheBuilder.newBuilder()

            //expireAfterAccess(long, TimeUnit) 这个方法是根据某个键值对最后一次访问之后多少时间后移除
            .expireAfterAccess(30, TimeUnit.SECONDS)
            //expireAfterWrite(long, TimeUnit) 这个方法是根据某个键值对被创建或值被替换后多少时间移除
            //.expireAfterWrite(1, TimeUnit.SECONDS)
            .initialCapacity(100)
            //CacheBuilder.recordStats()用来开启Guava Cache的统计功能。统计打开后，Cache.stats()方法会返回CacheStats对象以提供一些统计信息。具体信息可以查看CacheStats类的定义。
            .recordStats().build(
                    new CacheLoader<Object, Map<String, Object>>() {
                        @Override
                        public Map<String, Object> load(Object key) throws Exception {
                            if (key instanceof String) {
                                Map<String, Object> map = Maps.newHashMap();
                                map.putIfAbsent((String) key, new Entity.Student(1, (String) key,
                                        new Entity.Course("语文", 99.00)));
                                return map;
                            }
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
        Map<String, Object> map = guavaCacheDemo.guavaCache.get("张三");

        CacheStats cacheStats = guavaCacheDemo.guavaCache.stats();
        System.out.println(cacheStats.toString());

        map.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);

        });

        Ordering ordering = Ordering.arbitrary();

    }
}
