package com.github.whvixd.demo.jdk.thread;

import com.github.whvixd.util.InvokeTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * Semaphore 限流
 * Created by wangzhx on 2020/4/20.
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 只允许5个资源
        Semaphore semaphore = new Semaphore(5);
        IntStream.range(0, 10).forEach(e -> {
            InvokeTask invokeTask = InvokeTask.newInstance(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                //业务
                System.out.println("doing");
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                semaphore.release();
            });
            executorService.execute(invokeTask);
        });

        System.out.println(semaphore.getQueueLength());
        executorService.shutdown();
    }
}
