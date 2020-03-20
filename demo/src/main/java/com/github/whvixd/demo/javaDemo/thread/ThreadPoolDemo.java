package com.github.whvixd.demo.javaDemo.thread;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * 1
 * corePoolSize int 核心线程池大小
 * 2
 * maximumPoolSize int 最大线程池大小
 * 3
 * keepAliveTime long 线程最大空闲时间
 * 4
 * unit TimeUnit 时间单位
 * 5
 * workQueue BlockingQueue<Runnable> 线程等待队列
 * 6
 * threadFactory ThreadFactory 线程创建工厂
 * 7
 * handler RejectedExecutionHandler 拒绝策略
 * <p>
 * reference https://www.jianshu.com/p/f030aa5d7a28
 */
public class ThreadPoolDemo {

    public ThreadPoolExecutor initThreadPool(int corePoolSize,
                                             int maximumPoolSize,
                                             long keepAliveTime) {
        //无界阻塞队列
        /**
         * 队列最大值为Integer.MAX_VALUE。如果任务提交速度持续大余任务处理速度，会造成队列大量阻塞。因为队列很大，很有可能在拒绝策略前，内存溢出。
         * 是其劣势；
         * FixedThreadPool的任务执行是无序的；
         */
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
    }

    public ThreadPoolExecutor initAsynThreadPool(int corePoolSize,
                                                 int maximumPoolSize,
                                                 long keepAliveTime) {
        /**
         * 同步队列
         * 这个队列类似于一个接力棒，入队出队必须同时传递，因为CachedThreadPool线程创建无限制，不会有队列等待，所以使用SynchronousQueue；
         */

        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new SynchronousQueue<>());
    }

    public static void main(String[] args) throws IOException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 1,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        executor.prestartAllCoreThreads();//启动所有核心线程
        Runnable runnable = () -> {
            //do something
            System.out.println("run");
        };
        IntStream.range(0, 10).forEach(e -> {
            executor.execute(runnable);
            BlockingQueue<Runnable> queue = executor.getQueue();
            System.out.println(queue.size());
        });
        if (!executor.isShutdown()) {
            executor.shutdown();
        }

//        System.in.read();//阻塞主线程
    }
}
