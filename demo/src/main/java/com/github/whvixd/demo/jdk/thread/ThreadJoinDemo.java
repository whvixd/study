package com.github.whvixd.demo.jdk.thread;

import com.github.whvixd.util.InvokeTask;

/**
 * Created by wangzhx on 2020/3/16.
 */
public class ThreadJoinDemo {
    public static void main(String[] args) {

        InvokeTask invokeTask = InvokeTask.newInstance(() -> {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("InvokeTask-1");
        });

        invokeTask.start();
        System.out.println(invokeTask.getState());
        try {
            // 方法直接是synchronized修饰，对象锁是线程，调用wait进入 ObjectMonitor wait set 当其他线程(主线程)结束后，会调用notifyAll，唤醒所有线程
            invokeTask.join(100);// 等待invokeTask线程执行结束,100毫秒等待执行结果，若未结束，执行当前线程
            System.out.println(invokeTask.getState());
//            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("main thread end");

    }
}
