package com.github.whvixd.demo.javaDemo.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by wangzhx on 2019/8/13.
 */
public class FileChannelDemo {
    private void readFile() {
        try (RandomAccessFile file = new RandomAccessFile("/Users/whvixd/Documents/IdeaProjects/workspace/study/demo/src/main/resources/test/test.log", "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int read = channel.read(byteBuffer);
            while (read != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.print((char)byteBuffer.get());
                }
                byteBuffer.compact();
                read = channel.read(byteBuffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FileChannelDemo().readFile();
    }

}
