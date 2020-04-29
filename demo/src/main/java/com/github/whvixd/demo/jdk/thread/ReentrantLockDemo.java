package com.github.whvixd.demo.jdk.thread;

import com.github.whvixd.util.InvokeTask;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * Created by wangzhx on 2020/4/26.
 */
public class ReentrantLockDemo {
    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();
    Map<Integer,Integer> map = new HashMap<>();

    public void read() {
        readLock.lock();
        map.put(1,1);
        System.out.println(map);
        readLock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();

        IntStream.range(0,10).forEach(e->{
            InvokeTask.newInstance(()->{
                demo.read();
            }).start();
        });

    }
}
