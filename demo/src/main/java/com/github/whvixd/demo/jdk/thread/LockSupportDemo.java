package com.github.whvixd.demo.jdk.thread;

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
        LockSupport.park(this);
    }

    private void unlock(){
        LockSupport.unpark(map.get("1"));
    }

    public static void main(String[] args) {
        LockSupportDemo demo = new LockSupportDemo();
        demo.lock();
        demo.unlock();
    }
}
