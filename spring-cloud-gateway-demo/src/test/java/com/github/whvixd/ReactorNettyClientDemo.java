package com.github.whvixd;

import reactor.core.publisher.Flux;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;

/**
 * Created by wangzhx on 2022/9/18.
 */
public class ReactorNettyClientDemo {
    public static void main(String[] args) {
        HttpClient.create()             // Prepares an HTTP client ready for configuration
                .port(1118)  // Obtains the server's port and provides it as a port to which this
                // client should connect
                .post()               // Specifies that POST method will be used
                .uri("/test/World")   // Specifies the path
                .send(ByteBufFlux.fromString(Flux.just("Hello")))  // Sends the request body
                .responseContent()    // Receives the response body
                .aggregate()
                .asString()
                .log("http-client")
                .block();

    }
}
