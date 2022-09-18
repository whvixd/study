package com.github.whvixd.springbootdemo.service;

import com.github.whvixd.springbootdemo.client.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangzhx on 2022/9/17.
 */
@Service
@Slf4j
public class HelloService {
    @Autowired
    private EurekaClient eurekaClient;

    public String helloClient(String helloHeader){
        log.info("HelloService->helloClient,helloHeader:{}",helloHeader);
        return eurekaClient.helloClient();
    }
}
