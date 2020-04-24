package com.github.whvixd.demo.jdk.thread;

import lombok.Getter;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangzhx on 2020/3/8.
 */
public class ThreadWaitDemo {
    private AtomicInteger atomicInteger = new AtomicInteger();

    @Getter
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private static ThreadWaitDemo threadWaitDemo = new ThreadWaitDemo();

    private ThreadWaitDemo() {
    }

    public static ThreadWaitDemo instance() {
        return threadWaitDemo;
    }

    @Getter
    private final BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(100);


//    public void consume() {
//        synchronized (queue) {
//            while (true) {
//                if (queue.isEmpty()) {
//                    try {
//                        System.out.println("consume wait");
//                        queue.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    try {
//                        System.out.println(queue.take());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//    public void produce(int i) {
//        synchronized (queue) {
//            System.out.println("add " + i);
//            queue.offer(i);
//            if(queue.size()==99){
//                System.out.println("produce is stop");
//                Thread.currentThread().stop();
//            }
//            queue.notify();
////            if (queue.size() >= 5) {
////                queue.notify();
////                try {
////                    System.out.println("produce wait");
////                    queue.wait();
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////            }
//        }
//    }


    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        Producer producer = new Producer();

        new Thread(() -> {
            consumer.doConsume();
        }).start();


        new Thread(() -> {
            while (true) {
                producer.doProduct(1);
            }
        }).start();
    }
}
