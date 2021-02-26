package com.github.whvixd.demo.jdk.nioSocket;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by wangzhixiang on 2021/2/26.
 */
public class SendFileDemo {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",9999));
        FileInputStream fileInputStream=new FileInputStream("/xxx/xxx");
        FileChannel channel = fileInputStream.getChannel();
        // sendfile 依赖于操作系统内核函数,transferTo0
        // tomcat:Socket.sendfilen 实现了native sendfile方法
        channel.transferTo(0,channel.size(),socketChannel);
    }
}
