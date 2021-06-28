package com.github.whvixd.util;

import cn.hutool.core.collection.CollectionUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Created by wangzhixiang on 2021/06/23.
 */
@UtilityClass
@Slf4j
public class PageConsumerHelper {
    /**
     * 分页处理器
     *
     * @param minId          最小id
     * @param maxId          最大id
     * @param pageSize       分页数
     * @param sleepMSeconds  短暂睡眠(单位：毫秒)
     * @param consumer       业务代码
     */
    public void process(int minId, int maxId, int pageSize, long sleepMSeconds, BiConsumer<Integer, Integer> consumer) {
        if (consumer == null || minId > maxId || minId < 0 || maxId <= 0 || pageSize <= 0) {
            log.info("PageConsumerHelper->process,illegal args,minId:{},maxId:{},pageSize:{}", minId, maxId, pageSize);
            return;
        }

        log.info("PageConsumerHelper->process start,minId:{},maxId:{},pageSize:{},sleepMSeconds:{}ms", minId, maxId, pageSize, sleepMSeconds);
        StopWatch stopWatch = new StopWatch("PageConsumerHelper");
        int startIndex = minId, endIndex = (startIndex + pageSize >= maxId) ? maxId + 1 : (startIndex + pageSize);
        stopWatch.start("process");
        while (true) {
            if (startIndex > maxId) {
                break;
            }
            try {
                // 业务处理边界：[startIndex,endIndex)
                consumer.accept(startIndex, endIndex);
                log.info("PageConsumerHelper->process,accept success,startIndex:{},endIndex:{}", startIndex, endIndex);
            } catch (Exception e) {
                log.warn("PageConsumerHelper->process error,startIndex:{},endIndex:{},ex:", startIndex, endIndex, e);
            }
            startIndex = endIndex;
            endIndex = (endIndex + pageSize >= maxId) ? maxId + 1 : (startIndex + pageSize);

            if (sleepMSeconds>0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(sleepMSeconds);
                } catch (InterruptedException e) {
                    log.error("PageConsumerHelper->process error,ex:", e);
                }
            }
        }
        stopWatch.stop();
        log.info("PageConsumerHelper->process end,stopWatch:{}", stopWatch.prettyPrint());
    }

    public void process(int minId, int maxId, int pageSize, BiConsumer<Integer, Integer> consumer) {
        process(minId, maxId, pageSize, 0, consumer);
    }

    public <T> void process(int minId, int maxId, int pageSize, long sleepMSeconds, BiFunction<Integer, Integer, List<T>> function, Consumer<T> consumer) {
        if (consumer == null || minId > maxId || minId < 0 || maxId <= 0 || pageSize <= 0) {
            log.info("PageConsumerHelper->process,illegal args,minId:{},maxId:{},pageSize:{}", minId, maxId, pageSize);
            return;
        }

        log.info("PageConsumerHelper->process start,minId:{},maxId:{},pageSize:{},sleepMSeconds:{}ms", minId, maxId, pageSize, sleepMSeconds);
        StopWatch stopWatch = new StopWatch("PageConsumerHelper");
        int startIndex = minId, endIndex = (startIndex + pageSize >= maxId) ? maxId + 1 : (startIndex + pageSize);
        stopWatch.start("process");
        while (true) {
            if (startIndex > maxId) {
                break;
            }

            // 建议：[startIndex,endIndex)
            List<T> result = function.apply(startIndex, endIndex);
            if (CollectionUtil.isNotEmpty(result)) {
                log.info("PageConsumerHelper->process,function process start,startIndex:{},endIndex:{}", startIndex, endIndex);
                result.forEach(e -> {
                    try {
                        consumer.accept(e);
                        log.info("PageConsumerHelper->process,accept success,e:{}", e);
                    } catch (Exception ex) {
                        log.warn("PageConsumerHelper->process error,ex:", ex);
                    }
                });
                log.info("PageConsumerHelper->process,function process end,startIndex:{},endIndex:{}", startIndex, endIndex);
            }


            startIndex = endIndex;
            endIndex = (endIndex + pageSize >= maxId) ? maxId + 1 : (startIndex + pageSize);

            if (sleepMSeconds>0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(sleepMSeconds);
                } catch (InterruptedException e) {
                    log.error("PageConsumerHelper->process error,ex:", e);
                }
            }
        }
        stopWatch.stop();
        log.info("PageConsumerHelper->process end,stopWatch:{}", stopWatch.prettyPrint());

    }

    public <T> void process(int minId, int maxId, int pageSize, BiFunction<Integer, Integer, List<T>> function, Consumer<T> consumer) {
        process(minId, maxId, pageSize, 0, function, consumer);
    }


}
