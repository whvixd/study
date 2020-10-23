package com.github.whvixd.demo.jdk.util.current;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangzhixiang on 2020/10/23.
 */
public class CompletableFutureDemo {
    private static void getWithTimeout() throws Exception {
        // 异步处理
        CompletableFuture
                .supplyAsync(() -> {
                    int i = 0;
                    i += 1000;
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i *= 10;
                    System.out.println("supplyAsync");
                    return i;
                })
                // 对上述方法的结果处理
                .thenAccept(i -> {
                    System.out.println("thenAccept");
                })
                // 上一结果是Consumer，则无结果
                .get();
    }

    private static void get() throws Exception {
        // 异步处理
        Integer integer = CompletableFuture.supplyAsync(() -> {
            int i = 0;
            i += 1001;
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i *= 10;
            System.out.println("supplyAsync");
            return i;
        }).thenApplyAsync((value) -> {
            System.out.println("thenApplyAsync");
            return value - 1;
            // 与join不同的是，等待结果可能会中断
        }).get();
        System.out.println("get():" + integer);
    }

    private static void join() throws Exception {
        // 异步处理
        Integer integer = CompletableFuture.supplyAsync(() -> {
            int i = 0;
            i += 10000;
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i *= 10;
            System.out.println("supplyAsync");
            return i;
        }).thenApplyAsync((value) -> {
            System.out.println("thenApplyAsync");
            return value - 1;
        }).join();
        System.out.println("join():" + integer);
    }

    public static void main(String[] args) throws Exception {
//        getWithTimeout();
        join();
        get();
    }
}
