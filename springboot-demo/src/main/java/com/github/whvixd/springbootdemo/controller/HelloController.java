package com.github.whvixd.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangzhixiang on 2021/05/30.
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello!";
    }
}
