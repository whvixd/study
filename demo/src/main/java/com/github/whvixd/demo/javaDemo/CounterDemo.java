package com.github.whvixd.demo.javaDemo;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch,构造器添加次数，调用countDown()，减少一次，
 * await()，必须当次数==0时，才继续执行，不然一直等待。
 *
 * Created by wangzhx on 2018/3/12 17:24
 */
public class CounterDemo {
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("结束");
        }));//程序安全结束

        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            Thread.currentThread().setName("---1---");
            System.out.println(Thread.currentThread().getName()+"正在执行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行完毕");
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            Thread.currentThread().setName("---2---");
            System.out.println(Thread.currentThread().getName()+"正在执行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行完毕");
            countDownLatch.countDown();
        }).start();

        System.out.println("等待2个子线程执行完毕...");
        countDownLatch.await();

        System.out.println("end...");
    }

}
