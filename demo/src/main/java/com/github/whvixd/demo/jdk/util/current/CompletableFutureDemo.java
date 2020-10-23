package com.github.whvixd.demo.jdk.util.current;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * todo post blog
 * <p>
 * https://www.jianshu.com/p/6f3ee90ab7d3/
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1306581182447650
 * <p>
 * CompletableFuture可以指定异步处理流程：<br>
 * thenAccept() 处理正常结果；<br>
 * exceptional() 处理异常结果；<br>
 * thenApplyAsync() 用于串行化另一个CompletableFuture；<br>
 * anyOf()和allOf() 用于并行化多个CompletableFuture。<br>
 * <p>
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

    private static void thenAccept() throws Exception {
        // 代码中异步，等待上一操作的执行结果，串行
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("supplyAsync end");
            return "thenAccept";
        });

        CompletableFuture<Integer> integerCompletableFuture = completableFuture.thenApplyAsync((name) -> {
            System.out.println("thenApplyAsync start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thenApplyAsync end");
            return 100;
        });

        integerCompletableFuture.thenAccept(System.out::println);
    }

    private static void anyOf() {
        // 并行，anyOf合并操作，对先执行完成的线程结果执行下一操作
        CompletableFuture<Object> objectCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync_1 start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("supplyAsync_1 end");
            return "test_1";
        });

        CompletableFuture<Object> objectCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync_2 start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("supplyAsync_2 end");
            return "test_2";
        });
        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(objectCompletableFuture1, objectCompletableFuture2);
        objectCompletableFuture.thenAccept(System.out::println);

        CompletableFuture<Integer> integerCompletableFuture1 = objectCompletableFuture.thenApplyAsync(name -> {
            System.out.println("thenApplyAsync_1 start,name:" + name);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thenApplyAsync_1 end");
            return 100;
        });

        CompletableFuture<Integer> integerCompletableFuture2 = objectCompletableFuture.thenApplyAsync(name -> {
            System.out.println("thenApplyAsync_2 start,name:" + name);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thenApplyAsync_2 end");
            return 200;
        });

        CompletableFuture.allOf(integerCompletableFuture1, integerCompletableFuture2).join();

    }

    public static void main(String[] args) throws Exception {
//        getWithTimeout();
//        join();
//        get();
//        thenAccept();
        anyOf();
        System.in.read();
    }
}
