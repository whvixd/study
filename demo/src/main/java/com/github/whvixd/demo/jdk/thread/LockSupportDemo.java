package com.github.whvixd.demo.jdk.thread;

import com.github.whvixd.util.InvokeTask;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by wangzhx on 2020/5/18.
 */
public class LockSupportDemo {
    private Map<String,Thread> map = new HashMap<>();
    private void lock(){
        map.put("1",Thread.currentThread());
        System.out.println("park");
        LockSupport.park(this);
        System.out.println("unpark");
    }

    private void unlock(){
        Thread thread = map.get("1");

        LockSupport.unpark(thread);

        Thread.State state = thread.getState();
        System.out.println("after state:"+state);
        System.out.println("after isInterrupted:"+thread.isInterrupted());
    }

    public static void main(String[] args) throws InterruptedException {
        LockSupportDemo demo = new LockSupportDemo();
        InvokeTask.newInstance(()->{
            demo.lock();
        }).start();
        Thread.sleep(1000);
        demo.unlock();
    }
}
