package com.github.whvixd.demo.jdk.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by wangzhixiang on 2021/06/04.
 */
public class ClientSocketDemo {
    private static SocketChannel socketChannel;
    private static Selector selector;
    public ClientSocketDemo()throws Exception {
        socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
        socketChannel.configureBlocking(false);
        selector=Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
    }
    public static void main(String[] args) throws Exception{
        ClientSocketDemo test=new ClientSocketDemo();


        ByteBuffer buffer=ByteBuffer.allocate(1024);
        buffer.put("hello server".getBytes());
        buffer.flip();
        while(buffer.hasRemaining()){
            test.socketChannel.write(buffer);
        }

        buffer.clear();
        socketChannel.socket().shutdownOutput();

        String response=receiveData(test.socketChannel);
        System.out.println(response);
    }

    public static String receiveData(SocketChannel socketChannel2) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String response = "";
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            byte[] bytes;
            int count = 0;
            while ((count = socketChannel2.read(buffer)) >= 0) {
                buffer.flip();
                bytes = new byte[count];
                buffer.get(bytes);
                baos.write(bytes);
                buffer.clear();
            }

            bytes = baos.toByteArray();
            response = new String(bytes);
        } finally {
            try {
                baos.close();
            } catch (Exception ex) {
            }
        }
        return response;
    }






}
