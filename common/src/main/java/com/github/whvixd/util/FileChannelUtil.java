package com.github.whvixd.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by wangzhx on 2019/8/13.
 */
public class FileChannelUtil {
    public static String getFileContent(String fileName) {
         // mmap
        try (RandomAccessFile file = new RandomAccessFile(fileName, "r")) {
            FileChannel channel = file.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int read = channel.read(byteBuffer);
            StringBuilder stringBuffer = new StringBuilder();
            while (read != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    char c = (char) byteBuffer.get();
                    stringBuffer.append(c);
                }
                byteBuffer.compact();
                read = channel.read(byteBuffer);
            }

            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
