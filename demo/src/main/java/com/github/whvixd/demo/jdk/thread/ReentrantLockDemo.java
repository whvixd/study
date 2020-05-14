package com.github.whvixd.demo.jdk.thread;

import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Created by wangzhx on 2020/5/12.
 */
public class ReentrantLockDemo {
   private volatile int count;
   private final ReentrantLock lock = new ReentrantLock(true);

    public void add() {
        lock.lock();
        try {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            System.out.println(Thread.currentThread().getName()+" add,count:"+count);
        }finally {
            lock.unlock();
        }

    }


    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        IntStream.range(0, 100).forEach(e ->
                new Thread(demo::add).start());
    }
}
