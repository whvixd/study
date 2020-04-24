package com.github.whvixd.demo.jdk.thread;

import com.github.whvixd.util.InvokeTask;

/**
 * Created by wangzhx on 2020/3/16.
 */
public class ThreadJoinDemo {
    public static void main(String[] args) {

        InvokeTask invokeTask = InvokeTask.newInstance(() -> {

//            try {
//                Thread.currentThread().join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("InvokeTask-1");
        });

        invokeTask.start();
        System.out.println(invokeTask.getState());
        try {
            // 方法直接是synchronized修饰，对象锁是线程，调用wait进入 ObjectMonitor wait set 当其他线程(主线程)结束后，会调用notifyAll，唤醒所有线程
            invokeTask.join(100);
            System.out.println(invokeTask.getState());
//            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("main thread end");

    }
}
