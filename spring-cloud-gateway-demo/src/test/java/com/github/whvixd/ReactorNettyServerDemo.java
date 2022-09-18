package com.github.whvixd;

import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;

/**
 * https://github.com/reactor/reactor-netty
 *
 * Created by wangzhx on 2022/9/18.
 */
public class ReactorNettyServerDemo {
    public static void main(String[] args) throws IOException {

        DisposableServer param = HttpServer.create()   // Prepares an HTTP server ready for configuration
                .port(1118)    // Configures the port number as zero, this will let the system pick up
                // an ephemeral port when binding the server
                .route(routes ->
                        // The server will respond only on POST requests
                        // where the path starts with /test and then there is path parameter
                        routes.post("/test/{param}", (request, response) ->
                                response.sendString(request.receive()
                                        .asString()
                                        .map(s -> s + ' ' + request.param("param") + '!')
                                        .log("http-server"))))
                .bindNow();// Starts the server in a blocking fashion, and waits for it to finish its initialization
        System.out.println(param.host() + ":" + param.port());

        System.in.read();
    }
}
