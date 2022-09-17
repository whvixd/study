package com.github.whvixd.springbootdemo.controller;

import com.github.whvixd.springbootdemo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangzhixiang on 2021/05/30.
 */
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        return "hello!";
    }

    @GetMapping("/hello/eureka/client")
    public String helloEurekaClient() {
        return "hello eureka client!";
    }

    // 通过eureka注册的serveId获取到目标服务ip/port，也就是上面那方法
    @GetMapping("/web/entrance")
    public String webEntrance() {
        return helloService.helloClient();
    }
}
