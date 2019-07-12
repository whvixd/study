package com.github.whvixd.testDemo;

import com.github.whvixd.demo.javaDemo.socket.SocketClient;
import com.github.whvixd.demo.javaDemo.socket.SocketServer;
import org.junit.Test;

/**
 * 1. 先启动 SocketServer 跑testServer()
 * 2. 在启动 SocketClient 跑testClient()
 * Created by wangzhx on 2018/10/12.
 */
public class TestSocket {
    @Test
    public void testServer() {
        SocketServer socketServer = new SocketServer();
        System.out.println(socketServer.startServer());
    }

    @Test
    public void testClient() {
        SocketClient socketClient = new SocketClient();
        socketClient.startClient("hello world");
    }
}
