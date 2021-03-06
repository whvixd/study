package com.github.whvixd.demo.guava;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * 限流
 * Created by wangzhx on 2019/10/12.
 */
public class RateLimiterDemo {
    //令牌数
    private final RateLimiter rateLimiter = RateLimiter.create(1.0); // rate is "2 permits per second"


    private void submitTasks(List<Runnable> tasks, Executor executor) {
        for (Runnable task : tasks) {
            //下次生成令牌的时间 秒
            rateLimiter.acquire(); // may wait
            executor.execute(task);
        }
    }

    public static void main(String[] args) {
        RateLimiterDemo demo = new RateLimiterDemo();
        //四个线程同时进行，一秒只执行一个线程
        demo.submitTasks(Lists.newArrayList(() -> System.out.println("111"), () -> System.out.println("222"), () -> System.out.println("333"),
                () -> System.out.println("444")), Runnable::run);
    }
}
