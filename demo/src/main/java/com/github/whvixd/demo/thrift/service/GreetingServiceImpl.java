package com.github.whvixd.demo.thrift.service;

import org.apache.thrift.TException;

import java.util.logging.Logger;

/**
 * Created by wangzhx on 2020/5/19.
 */
public class GreetingServiceImpl {
    private static final Logger logger =  Logger.getLogger(GreetingServiceImpl.class.getName());

    public String sayHello(String name) throws TException {
        logger.info(String.format("调用sayHello方法的参数name = {%s}", name));

        return "Hello, " + name;
    }
}
