package com.github.whvixd.springbootdemo.service;

import com.github.whvixd.springbootdemo.client.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangzhx on 2022/9/17.
 */
@Service
public class HelloService {
    @Autowired
    private EurekaClient eurekaClient;

    public String helloClient(){
        return eurekaClient.helloClient();
    }
}
