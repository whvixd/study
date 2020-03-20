package com.github.whvixd.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by wangzhx on 2020/3/15.
 */
public class UnsafeInstance {
    public static Unsafe getInstance() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        InvokeTask invokeTask = InvokeTask.newInstance(() -> {
//            while (true){
//                if(Thread.currentThread().isInterrupted()){
//                    System.out.println("---");
//                }
//            }
//        });
//        invokeTask.start();
//        invokeTask.interrupt();
//        System.out.println("1");
//        System.out.println(invokeTask.isInterrupted());

        Thread thread = Thread.currentThread();
        thread.interrupt();
        System.out.println(thread.isInterrupted());
        System.out.println(thread.getState());
    }
}
