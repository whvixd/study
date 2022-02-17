package com.github.whvixd.springbootdemo.config;

import com.github.whvixd.springbootdemo.common.util.CommonExecutorPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;

/**
 * Created by wangzhixiang on 2021/04/29.
 */
@Configuration
public class SpringConfig {

    @Bean
    public ExecutorService commonExecutorPool(){
        return CommonExecutorPool.getThreadPoolExecutor();
    }
}
