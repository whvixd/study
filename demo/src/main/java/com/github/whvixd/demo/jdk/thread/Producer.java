package com.github.whvixd.demo.jdk.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangzhx on 2020/3/8.
 */
public class Producer {

    private final ThreadWaitDemo threadWaitDemo = ThreadWaitDemo.instance();
    private final ReentrantLock reentrantLock = threadWaitDemo.getReentrantLock();

    public void doProduct(int i) {
        BlockingQueue<Integer> queue = threadWaitDemo.getQueue();

        synchronized (reentrantLock) {
            System.out.println("add " + i);
            queue.offer(i);
            if (queue.size() == 99) {
                System.out.println("produce is stop");
                Thread.currentThread().stop();
            }
            reentrantLock.notify();
//            if (queue.size() >= 5) {
//                queue.notify();
//                try {
//                    System.out.println("produce wait");
//                    queue.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }
}
