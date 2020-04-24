package com.github.whvixd.demo;

import com.github.whvixd.demo.jdk.socket.SocketClient;
import com.github.whvixd.demo.jdk.socket.SocketServer;
import org.junit.Test;

import java.util.Scanner;

/**
 * 1. 先启动 SocketServer 跑testServer()
 * 2. 在启动 SocketClient 跑testClient()
 * 聊天室 https://blog.csdn.net/dac55300424/article/details/9789689
 * Created by wangzhx on 2018/10/12.
 */
public class TestSocket {
    @Test
    public void testServer() {
        SocketServer socketServer = new SocketServer();
        //接口返回
//        System.out.println(socketServer.startServer());
        socketServer.startServerForever();
    }

    @Test
    public void testClient() {

    }

    //https://blog.csdn.net/alexwym/article/details/81223764
    public static void main(String[] args) {
        while (true) {
            SocketClient socketClient = new SocketClient();
            Scanner scanner = new Scanner(System.in);
            //需要接口，传入输入的内容，
            //在那个页面
            socketClient.startClient(scanner.nextLine());
        }
    }
}
