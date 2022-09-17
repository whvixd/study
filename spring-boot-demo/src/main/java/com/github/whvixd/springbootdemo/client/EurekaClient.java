package com.github.whvixd.springbootdemo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by wangzhx on 2022/9/17.
 */
@FeignClient(value = "eureka-client", path = "/hello")
public interface EurekaClient {

    @GetMapping("/eureka/client")
    String helloClient();

}
