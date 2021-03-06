package com.github.whvixd.demo.jdk.thread;

import java.util.function.Supplier;

/**
 * Created by wangzhx on 2018/3/23 17:40.
 *
 * ThreadLocal 为当前线程绑定一个变量，可以通过withInitial(supplier) 和 set(object)方法设置变量
 * 通过get()获取变量
 */
public class ThreadLocalDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            ThreadLocal threadLocal = new ThreadLocal();
            threadLocal.set("---");
            System.out.println(threadLocal.get());
        });
        thread.start();

        Supplier<String> supplier = ()-> "hahah";
        ThreadLocal t = ThreadLocal.withInitial(supplier);
        System.out.println(t.get());


        String s = "local thread";
        String s1 = "other";
        int a = 1;
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set(s);
        threadLocal.set(s1);
        threadLocal.set(a);

        // get实际是从当前线程的Map中获取，和new ThreadLocal()没有关系，如果再次new ThreadLocal()，获取get方法还是获取一开始的set方法
        int b = (int) threadLocal.get();
        System.out.println(threadLocal.get());
        System.out.println(b);

    }
}
