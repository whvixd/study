package com.github.whvixd.demo.jdk.nioSocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by wangzhx on 2020/3/8.
 */
public class Client {
    public void start() {
        try {
            try (SocketChannel socketChannel = SocketChannel.open()) {
                //连接服务端socket
                SocketAddress socketAddress = new InetSocketAddress("localhost", 1118);
                socketChannel.connect(socketAddress);

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.clear();
                //向服务端发送消息
                buffer.put(("current time : " + System.currentTimeMillis()).getBytes());
                //这里最好使用selector处理   这里只是为了写的简单
                while (true) {

                    //读取模式
                    buffer.flip();
                    socketChannel.write(buffer);
                    buffer.clear();

                    //从服务端读取消息
                    int readLenth = socketChannel.read(buffer);
                    //读取模式
                    buffer.flip();
                    byte[] bytes = new byte[readLenth];
                    buffer.get(bytes);
                    System.out.println(new String(bytes, "UTF-8"));
                    buffer.clear();


//                    sendCount++;
                    System.out.println("loop");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().start();
    }
}
