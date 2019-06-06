package com.github.whvixd.demo.javaDemo;


import com.github.whvixd.util.StreamUtil;

import java.io.*;
import java.net.Socket;

/**
 * Created by wangzhx on 2018/10/12.
 */
public class SocketClient {
    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_PORT = 1118;

    public void startClient(String message) {
        try {
            Socket socket = new Socket(DEFAULT_HOST, DEFAULT_PORT);
            OutputStream outputStream = socket.getOutputStream();
            StreamUtil.writeStreamWithString(outputStream, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
