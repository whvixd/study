package com.github.whvixd.demo.jdk.thread;

import com.github.whvixd.util.InvokeTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 等待其他线程-1操作，到0，会执行 await方法
 * Created by wangzhx on 2020/3/7.
 */
public class CountDownLatchDemo {


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        Executor executor = Executors.newFixedThreadPool(100);


        InvokeTask boss = InvokeTask.newInstance(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("boss," + countDownLatch.getCount());
        }).invokeTaskName("boss");

        IntStream.range(0, 100).forEach(k -> {
            InvokeTask worker = InvokeTask.newInstance(() -> {
                countDownLatch.countDown();
                System.out.println(countDownLatch.getCount());
                System.out.println(Thread.currentThread().getName());
            }).invokeTaskName("worker_" + k);
            executor.execute(worker);
        });
        executor.execute(boss);
    }
}
