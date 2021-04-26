package com.github.whvixd.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * Created by wangzhixiang on 2021/04/25.
 */
public class CommonExecutorPoolTest {
    private static Logger log= LoggerFactory.getLogger(CommonExecutorPoolTest.class);


    @Test
    public void test1(){
        ExecutorService threadPoolExecutor = CommonExecutorPool.getThreadPoolExecutor();

        CompletableFuture.runAsync(()->{

        });
    }

    @Test
    public void test2(){
        MDC.put("traceId","traceIdA");
        log.info("this is A");
        log.info("this is B");
    }

    public static void main(String[] args) {
        MDC.put("traceId","traceIdA");
        log.info("this is A");
        log.info("this is B");
    }

}
