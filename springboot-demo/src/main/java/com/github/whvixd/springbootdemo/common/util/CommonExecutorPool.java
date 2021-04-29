package com.github.whvixd.springbootdemo.common.util;

import com.github.whvixd.util.MDCUtil;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

/**
 * Created by wangzhixiang on 2021/04/24.
 */
public class CommonExecutorPool {
    private static int threadCount = Runtime.getRuntime().availableProcessors() * 2;
    private static final int MAX_THREAD_COUNT = 20;
    private static ExecutorService threadPoolExecutor =
            new ThreadPoolExecutor(threadCount, MAX_THREAD_COUNT,
                    1L,
                    TimeUnit.MINUTES,
                    new LinkedBlockingQueue<>(256),
                    new CustomizableThreadFactory("common-task-"),
                    new ThreadPoolExecutor.CallerRunsPolicy()) {

                @Override
                public void execute(Runnable task) {
                    super.execute(MDCUtil.wrap(task));
                }

                @Override
                public <T> Future<T> submit(Runnable task, T result) {
                    return super.submit(MDCUtil.wrap(task), result);
                }

                @Override
                public <T> Future<T> submit(Callable<T> task) {
                    return super.submit(MDCUtil.wrap(task));
                }

                @Override
                public Future<?> submit(Runnable task) {
                    return super.submit(MDCUtil.wrap(task));
                }
            };

    public static ExecutorService getThreadPoolExecutor() {
        return threadPoolExecutor;
    }
}
