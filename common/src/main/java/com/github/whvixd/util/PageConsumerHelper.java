package com.github.whvixd.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

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
     * @param temporarySleep 是否每执行完短暂睡眠
     * @param consumer       业务代码
     */
    public void process(int minId, int maxId, int pageSize, boolean temporarySleep, BiConsumer<Integer, Integer> consumer) {
        if (consumer == null || minId > maxId || minId < 0 || maxId <= 0 || pageSize <= 0) {
            log.info("PageConsumerHelper->process,illegal args,minId:{},maxId:{},pageSize:{}", minId, maxId, pageSize);
            return;
        }

        log.info("PageConsumerHelper->process start,minId:{},maxId:{},pageSize:{},temporarySleep:{}",minId,maxId,pageSize,temporarySleep);
        StopWatch stopWatch = new StopWatch("PageConsumerHelper");
        int startIndex = minId, endIndex = (startIndex+pageSize>=maxId)?maxId+1:(startIndex + pageSize);
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
            endIndex = (endIndex+pageSize>=maxId)?maxId+1:(startIndex + pageSize);

            if (temporarySleep) {
                try {
                    // 100ms
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    log.error("PageConsumerHelper->process error,ex:", e);
                }
            }
        }
        stopWatch.stop();
        log.info("PageConsumerHelper->process end,stopWatch:{}",stopWatch.prettyPrint());
    }

    public void process(int minId, int maxId, int pageSize, BiConsumer<Integer, Integer> consumer) {
        process(minId, maxId, pageSize, false, consumer);
    }


}
