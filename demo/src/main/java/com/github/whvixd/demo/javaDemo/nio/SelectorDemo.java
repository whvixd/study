package com.github.whvixd.demo.javaDemo.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;

/**
 * Created by wangzhx on 2019/7/26.
 */
public class SelectorDemo {
    public static void main(String[] args) throws IOException {
        //创建了一个1024个byte的数组的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Selector selector = Selector.open();
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        System.out.println(selectionKeys);

    }
}
