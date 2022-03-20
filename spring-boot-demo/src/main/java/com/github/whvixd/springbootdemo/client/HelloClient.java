package com.github.whvixd.springbootdemo.client;

import com.github.whvixd.restful.client.annotation.RequestGet;
import com.github.whvixd.restful.client.annotation.RequestMapping;

/**
 * Created by wangzhixiang on 2022/02/19.
 */
@RequestMapping
public interface HelloClient {

    @RequestGet(path = "/hello/get")
    String helloGet();

    String helloPost();
}
