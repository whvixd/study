package com.github.whvixd.demo.thrift.service;

import org.apache.thrift.TException;

/**
 * Created by wangzhixiang on 2021/06/25.
 */
public class HelloThriftServiceImpl implements HelloThriftService.Iface{
    @Override
    public String sayHello(String name) throws TException {
        return "hello "+name;
    }
}
