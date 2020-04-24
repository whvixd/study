package com.github.whvixd.util.datastructure;

import com.github.whvixd.util.InvokeTask;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Created by wangzhx on 2020/4/24.
 */
public class CLHLockTest {
    CLHLock clhLock = new CLHLock();
    int i =0;

    private void testLock(){
        clhLock.lock();
        i++;
        System.out.println(Thread.currentThread().getName()+":"+i);
        clhLock.unlock();
    }
    public static void main(String[] args) {
        CLHLockTest test = new CLHLockTest();


        IntStream.range(0,100).forEach(e->{
            new Thread(()->{
                test.testLock();
            }).start();
        });

//        ExecutorService executorService = Executors.newFixedThreadPool(20);
//        IntStream.range(0,20).forEach(e->
//                executorService.execute(invokeTask)
//        );
//        executorService.shutdown();

    }
}
