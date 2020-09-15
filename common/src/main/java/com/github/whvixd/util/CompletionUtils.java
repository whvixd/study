package com.github.whvixd.util;

import com.github.whvixd.util.function.AgentConsumer;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;

import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * Created by wangzhixiang on 2020/9/11.
 */
@UtilityClass
@Slf4j
public class CompletionUtils {
    // todo
    private static ExecutorThreadPool executorThreadPool = new ExecutorThreadPool();


    /**
     * @param threadCount 线程数
     * @param consumer    业务逻辑
     * @return 执行结果，有一个异常则都false
     */
    public boolean process(int threadCount, AgentConsumer consumer) {
        if (threadCount < 1 || consumer == null) {
            return false;
        }
        CompletionService<Boolean> completionService = new ExecutorCompletionService<>(executorThreadPool);
        AtomicBoolean result = new AtomicBoolean(Boolean.TRUE);
        IntStream.range(0, threadCount).forEach(i ->
                completionService.submit(() -> {
                    try {
                        consumer.accept();
                        return true;
                    } catch (Exception e) {
                        log.warn("CompletionUtils process error,threadCount:{}", threadCount, e);
                        return false;
                    }
                })
        );
        IntStream.range(0, threadCount).forEach(i -> {
            try {
                result.compareAndSet(Boolean.TRUE, completionService.take().get());
            } catch (Exception ex) {
                log.warn("completionService take get error, ", ex);
            }
        });
        return result.get();
    }


    // 注：consumer [finalStart,finalEnd)
    public boolean process(int shardingSize, int startIndex, int endIndex, BiConsumer<Integer, Integer> consumer) {
        int count = endIndex - startIndex + 1;
        if (shardingSize < 1 || count < 1 || shardingSize > count || consumer == null) {
            log.info("process check is false");
            return false;
        }

        CompletionService<Boolean> completionService = new ExecutorCompletionService<>(executorThreadPool);

        int exact = Math.toIntExact(count / shardingSize);
        // 线程数
        int shardingNumber = (count % shardingSize == 0) ? exact : exact + 1;
        int shardingStartIndex = startIndex;
        int shardingEndIndex = startIndex + shardingSize;

        for (int i = 0; i < shardingNumber; i++) {
            int finalStart = shardingStartIndex;
            int finalEnd = shardingEndIndex > endIndex ? endIndex + 1 : shardingEndIndex;
            completionService.submit(() -> {
                try {
                    consumer.accept(finalStart, finalEnd);
                    return true;
                } catch (Exception ex) {
                    log.warn("CompletionUtils process error,threadCount:{}", shardingNumber, ex);
                    return false;
                }
            });
            shardingStartIndex += shardingSize;
            shardingEndIndex = shardingStartIndex + shardingSize;
        }

        AtomicBoolean result = new AtomicBoolean(Boolean.TRUE);
        IntStream.range(0, shardingNumber).forEach(i -> {
            try {
                result.compareAndSet(Boolean.TRUE, completionService.take().get());
            } catch (Exception ex) {
                log.warn("completionService take get error, ", ex);
            }
        });
        return result.get();
    }


    /**
     * @param shardingSize 每片大小
     * @param startIndex   开始
     * @param endIndex     结束
     * @param list         处理的集合
     * @param consumer     业务,返回list 元素
     * @param <T>
     * @return
     */
    public <T> boolean process(int shardingSize, int startIndex, int endIndex, List<T> list, Consumer<T> consumer) {
        if (list == null || list.size() == 0) {
            return false;
        }
        return process(shardingSize, startIndex, endIndex,
                (start, end) ->
                {
                    if (start.equals(end)) {
                        consumer.accept(list.get(start));
                    } else {
                        IntStream.range(start, end).forEach(i -> {
                            T t = list.get(i);
                            if (t == null) return;
                            consumer.accept(t);
                        });
                    }

                });

    }

    public <T> boolean process(List<T> list, Consumer<T> consumer) {
        if (list == null || list.size() == 0) {
            return false;
        }
        int size = list.size();
        return process(size < 100 ? size : size / 5, 0, list.size() - 1, list, consumer);
    }
}
