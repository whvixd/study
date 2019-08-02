package com.github.whvixd.demo.javaDemo.socket;


import com.github.whvixd.util.StreamUtil;
import lombok.Setter;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

/**
 * Created by wangzhx on 2018/10/12.
 */
public class SocketClient {
    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_PORT = 1118;
    @Setter
    private Integer port;

    public void startClient(String message) {
        try {
            if (Objects.isNull(port)) {
                port = DEFAULT_PORT;
            }
            Socket socket = new Socket(DEFAULT_HOST, port);
            OutputStream outputStream = socket.getOutputStream();
            StreamUtil.writeStreamWithString(outputStream, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
