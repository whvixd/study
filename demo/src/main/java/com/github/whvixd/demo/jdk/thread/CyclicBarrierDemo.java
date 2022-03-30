package com.github.whvixd.demo.jdk.thread;

import com.github.whvixd.util.InvokeTask;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 回环栅栏
 *  CyclicBarrier 一般用于一组线程互相等待至某个状态
 * Created by wangzhixiang on 2022/03/30.
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2);
        InvokeTask.newInstance(() -> {
            System.out.println("线程1已就绪");
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("线程1开始");
        }).start();

        InvokeTask.newInstance(() -> {
            System.out.println("线程2已就绪");
            try {
                Thread.sleep(1000);
                barrier.await();// 等待所有线程都是barrier状态才会继续往下执行
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("线程2开始");
        }).start();
    }
}
