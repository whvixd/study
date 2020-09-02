package com.github.whvixd.demo.jdk.thread;

import com.google.common.collect.Lists;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangzhx on 2020/3/8.
 */
public class Consumer {
    private final ThreadWaitDemo threadWaitDemo =  ThreadWaitDemo.instance();
   private final ReentrantLock reentrantLock = threadWaitDemo.getReentrantLock();

    public void doConsume(){

        BlockingQueue<Integer> queue = threadWaitDemo.getQueue();
        synchronized (reentrantLock){
            while (true) {
                try {
                if(queue.size()==3){
                    // 将队列中值，全部移除，并发设置到给定的集合中。
                    queue.drainTo(Lists.newArrayList());
                    throw new RuntimeException();
                }
                if (queue.isEmpty()) {

                        System.out.println("consume wait");
                        reentrantLock.wait();

                } else {
                    try {
                        System.out.println(queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                } catch (Exception e) {
                    try {
                        System.out.println("exception===start");
                        reentrantLock.wait();
                        System.out.println("exception===stop");

                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

    }
}
