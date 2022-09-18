package com.github.whvixd.spring.cloud.gateway.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by wangzhx on 2022/9/18.
 */
@RestController
@Slf4j
public class WebFluxDemoController {

    /**
     * 浏览器出调用，每次返回一个结果
     * @return
     */
    @GetMapping(value = "hello/flux",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> helloFlux() {
        log.info("helloFlux start");
        Flux<String> rst = Flux.fromStream(IntStream.range(0, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello flux_" + i;
        }));
        log.info("helloFlux end");
        return rst;
    }

    @GetMapping("hello/mono")
    public Mono<String> helloMono(){
        log.info("helloMono start");
        // 对于调用者是5s后返回结果，但是内部是异步返回Mono对象，然后在Reactor中堵塞5s返回
        Mono<String> rst = Mono.fromSupplier(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello mono";
        });
        log.info("helloMono end");
        return rst;
    }
}
