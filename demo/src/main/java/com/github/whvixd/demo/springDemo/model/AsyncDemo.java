package com.github.whvixd.demo.springDemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created by wangzhx on 2019/7/9.
 */

/**
 * 1. 返回值：不要返回值直接void；需要返回值用AsyncResult或者CompletableFuture
 * 2. 可自定义执行器并指定例如：@Async("otherExecutor")
 * 3. @Async必须不同类间调用： A类—>B类.C方法()（@Async注释在B类/方法中），如果在同一个类中调用，会变同步执行,例如:A类.B()—>A类.@Async C()。
 * 4. @Async也可以加到类，表示这个类的所有方法都是异步执行，并且方法上的注解会覆盖类上的注解。但一般不这么用！
 */

//implements AsyncConfigurer 实现抱错
//@ToString
public class AsyncDemo {
    @Setter
    @Getter
    private String name = "AsyncDemo";

    @Setter
    @Autowired
    @Getter
    private Model1 model1;

    @Async
    public void asyncTest() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {

        }
        System.out.println("----asyncTest----");
    }

    public void aopDemo(){
        System.out.println("invoke this method");
    }

//    @Override
//    public Executor getAsyncExecutor() {
//        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
//        threadPool.setCorePoolSize(2);//当前线程数
//        threadPool.setMaxPoolSize(120);// 最大线程数
//        threadPool.setQueueCapacity(1);//线程池所使用的缓冲队列
//        threadPool.setWaitForTasksToCompleteOnShutdown(true);//等待任务在关机时完成--表明等待所有线程执行完
//        threadPool.setAwaitTerminationSeconds(60 * 15);// 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
//        threadPool.setThreadNamePrefix("MyAsync-");//  线程名称前缀
//        threadPool.initialize(); // 初始化
//        System.out.println("--------------------------》》》开启异常线程池");
//        return threadPool;
//    }
//
//    @Override
//    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//        return null;
//    }
}
