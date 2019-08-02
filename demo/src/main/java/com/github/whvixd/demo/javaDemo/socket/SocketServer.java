package com.github.whvixd.demo.javaDemo.socket;

import com.github.whvixd.util.StreamUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wangzhx on 2018/10/12.
 */
public class SocketServer {
    private static final int DEFAULT_PORT = 1118;

    public String startServer() {
        try (Socket accept = new ServerSocket(DEFAULT_PORT).accept()) {
            InputStream inputStream = accept.getInputStream();
            return StreamUtil.readStream2String(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void startServerForever() {
        try {
            ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);
            while (true) {
                Socket accept = serverSocket.accept();
                InputStream inputStream = accept.getInputStream();
                System.out.println(StreamUtil.readStream2String(inputStream));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void startServerForever(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket accept = serverSocket.accept();
                SocketServerTask task = new SocketServerTask();
                task.setAccept(accept);
                task.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
