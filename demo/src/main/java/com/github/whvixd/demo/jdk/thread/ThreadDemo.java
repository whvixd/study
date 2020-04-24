package com.github.whvixd.demo.jdk.thread;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by wangzhx on 2019/4/20.
 */
public class ThreadDemo {
    public volatile int temp;

    public int add() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return temp++;
    }

    public void thread() {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                int a;
                a = threadDemo.add();
                System.out.println("a:" + a);
                System.out.println("temp:" + temp);
            }
        }).start();
    }

    public void executorServiceTest() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        List<Callable<Integer>> runnable = Lists.newArrayList();


        Callable<Integer> callable1 = () -> {
            Thread.sleep(123L);
            System.out.println("123");
            return 1;
        };
        Callable<Integer> callable2 = () -> {
            System.out.println("456");
            return 2;
        };

        runnable.add(callable1);
        runnable.add(callable2);

        //堵塞
        try {
            List<Future<Integer>> futures = fixedThreadPool.invokeAll(runnable);
            futures.forEach(future -> {
                try {
                    System.out.println(future.get());
                } catch (Throwable e) {
                    throw new RuntimeException();
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException();

        }
        System.out.println("789");

        fixedThreadPool.shutdown();
    }

    public void executorServiceTest1() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        //不会堵塞
        fixedThreadPool.execute(() -> System.out.println("123"));

        System.out.println("789");

        fixedThreadPool.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
//        new ThreadDemo().thread();
        new ThreadDemo().executorServiceTest1();

    }


}
