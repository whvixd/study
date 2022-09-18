package com.github.whvixd.spring.cloud.gateway.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Created by wangzhx on 2022/9/18.
 */
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    // todo 动态路由可参考：https://www.jianshu.com/p/490739b183af
    @Bean
    public RouteLocator helloRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("hello",
                        p -> p.path("/web/{entrance}")
                                .filters(f -> f.addRequestHeader("hello", "route"))
                                .uri("http://localhost:9998").order(1))
                .build();
    }

}
