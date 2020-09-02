package com.github.whvixd.demo.jdk.thread;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 用来执行 callable 的线程池
 * https://blog.csdn.net/fxkcsdn/article/details/82316046
 * <p>
 * Created by wangzhixiang on 2020/6/23.
 */
public class CompletionServiceDemoTwo {

    public static void main(String[] args) {
        // 核心线程数=jvm的处理器数*2
        int cores = Runtime.getRuntime().availableProcessors() * 2;
        Executor pool = new ThreadPoolExecutor(cores, cores,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10),
                new DefaultThreadFactory("study"),
                new ThreadPoolExecutor.CallerRunsPolicy());

        // 线程池
        CompletionService<Boolean> service = new ExecutorCompletionService<>(pool);

        // 添加任务
        List<Callable<Boolean>> tasks = getTasks();

        // 执行任务
        IntStream.range(0, tasks.size()).forEach(e -> service.submit(tasks.get(e)));

        // 处理任务执行的结果
        Boolean result = getResult(tasks, service);
        System.out.println(result);

        // 程序结束
        System.exit(0);
    }

    private static List<Callable<Boolean>> getTasks() {
        List<Callable<Boolean>> tasks = new LinkedList<>();
        tasks.add(() -> {
            // doing something
            return true;
        });
        tasks.add(() -> {
            // doing something
            return true;
        });
        tasks.add(() -> {
            // doing something
            return true;
        });
        tasks.add(() -> {
            // doing something
            return true;
        });
        return tasks;
    }

    private static Boolean getResult(List<Callable<Boolean>> tasks, CompletionService<Boolean> service) {

        AtomicBoolean result = new AtomicBoolean(true);

        IntStream.range(0, tasks.size()).forEach(e -> {
            try {
                result.compareAndSet(true, service.take().get());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        return result.get();
    }
}
