package com.github.whvixd.util.datastructure;

import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Created by wangzhx on 2020/5/15.
 */
public class QueuedSynchronizerTest {
    private static final QueuedSynchronizer lock = new QueuedSynchronizer(true);
    private static final ReentrantLock reentrantLock = new ReentrantLock();
    private volatile int i;

    public void testLock(){

        lock.lock();
        try {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            System.out.println(Thread.currentThread().getName()+" ,i="+i);
        }finally {
            lock.unlock();
        }
    }

    public void testReenTrantLock(){
        reentrantLock.lock();
        try {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            System.out.println(Thread.currentThread().getName()+" ,i="+i);
        }finally {
            reentrantLock.unlock();
        }
    }


    private void printQueue(){
        QueuedSynchronizer.Node point = lock.getHead();
        if(point==null){
            return;
        }
        System.out.println("head:"+point.getThread().getName());
        while (point.getNext()!=null){
            System.out.println(point.getThread().getName());
            point=point.next;
        }
    }

    public static void main(String[] args) {
        QueuedSynchronizerTest instance = new QueuedSynchronizerTest();

        // 过多线程有问题
        IntStream.range(0,2000).forEach(e->{
            new Thread(()->{
                instance.testLock();
            }).start();
        });

//        printThread(instance);
    }

    private static void printThread(QueuedSynchronizerTest instance){
        new Thread(()->{
            while (true){

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("----print start----");
                instance.printQueue();
                System.out.println("----print end----");
            }
        }).start();
    }
}
