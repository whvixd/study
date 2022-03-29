package com.github.whvixd.demo.jdk.thread;

/**
 * 死锁
 * Created by wangzhixiang on 2022/03/29.
 */
public class DeadLockDemo {
    private static final Lock lock1 = new Lock();
    private static final Lock lock2 = new Lock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true)
                synchronized (lock1) {
                    System.out.println("thread1 tack lock1...");
                    synchronized (lock2) {//lock2 被线程2占用，线程1只能堵塞等待
                        System.out.println("thread1 tack lock2...");
                    }
                }
        });

        Thread thread2 = new Thread(() -> {
            while (true)
                synchronized (lock2) {
                    System.out.println("thread2 tack lock2...");
                    synchronized (lock1) {//lock1 被线程1占用，线程2只能堵塞等待
                        System.out.println("thread2 tack lock1...");
                    }
                }
        });

        thread1.start();
        thread2.start();
    }
}
