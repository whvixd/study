package com.github.whvixd.demo.jdk.thread;

import com.github.whvixd.util.InvokeTask;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangzhx on 2020/3/15.
 */
public class LockDemo {

    //公平锁，线程执行会按申请锁的顺序执行，缺点，时间片的利用率低，吞吐量低，若线程A拿到了资源，线程B过来正在堵塞，线程A正准备释放，
    // 线程C过来了，若是公平锁则需要等待B执行完释放锁，若是非公平锁则C获取锁资源，减少了状态的切换
    private final ReentrantLock reentrantLock = new ReentrantLock(true);

    private static final Lock lock = new Lock();


    // 对非静态方法加锁，针对同一个实例相当于  synchronized(this){}
    public synchronized void testMethod(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

    //  对静态方法加锁，针对同一个实例相当于  synchronized(LockDemo.class){}
    public synchronized static void testStaticMethod(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

    public void test1(){
        synchronized (lock){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

    public void test2(){
        synchronized (lock){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        LockDemo lockDemo1 = new LockDemo();
        System.out.println(lockDemo1.hashCode());
        LockDemo lockDemo2 = new LockDemo();
        System.out.println(lockDemo2.hashCode());
        InvokeTask.newInstance(()->{
            lockDemo1.test1();
        }).start();

        InvokeTask.newInstance(()->{
            lockDemo2.test2();
        }).start();

// --------------------------------
        InvokeTask.newInstance(()->{
            lockDemo1.testMethod();
        }).start();

        InvokeTask.newInstance(()->{
            lockDemo2.testMethod();
        }).start();

// --------------------------------
        InvokeTask.newInstance(()->{
            LockDemo.testStaticMethod();
        }).start();

        InvokeTask.newInstance(()->{
            LockDemo.testStaticMethod();
        }).start();
    }

}
