package com.github.whvixd.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileMode;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangzhx on 2019/8/13.
 */
public class FileChannelUtil {
    private MappedByteBuffer mappedByteBuffer;
    private String fileName;
    private File file;
    private FileChannel fileChannel;
    // 推外内存
    ByteBuffer writeBuffer;
    // 1G
    private static int fileSize = 1024 * 1024 * 1024;
    private int commitCommitLogLeastPages = 4;
    private int flushCommitLogLeastPages = 4;

    public static final int OS_PAGE_SIZE = 1024 * 4;

    // 写指针
    private final AtomicInteger wrotePosition = new AtomicInteger(0);
    /////////开启事务时/////////
    // 提交指针
    protected final AtomicInteger committedPosition = new AtomicInteger(0);
    // 刷盘指针
    private final AtomicInteger flushedPosition = new AtomicInteger(0);


    public void init(final String fileName) throws IOException {
        this.fileName = fileName;
        this.file = new File(fileName);
        FileUtil.mkdir(file.getParent());
        this.fileChannel = FileUtil.createRandomAccessFile(this.file, FileMode.rw).getChannel();
        // mmap
        this.mappedByteBuffer = this.fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
        this.writeBuffer = ByteBuffer.allocateDirect(fileSize);
    }

    // 直接写到文件中
    public boolean appendMessage(final byte[] data) {
        int currentPos = this.wrotePosition.get();

        if ((currentPos + data.length) <= fileSize) {
            try {
                // 移动到写指针
                this.fileChannel.position(currentPos);
                // 写文件
                this.fileChannel.write(ByteBuffer.wrap(data));
            } catch (Throwable e) {
                System.err.printf("Error occurred when append message to mappedFile.e:%s", e);
            }
            // 写完后再移动指针
            this.wrotePosition.addAndGet(data.length);
            return true;
        }

        return false;
    }

    // todo 先写到推外内存，再提交，最后刷盘，存到磁盘中
    public boolean write(final byte[] data) {
        int currentP = wrotePosition.get();
        if (data.length + currentP <= fileSize) {
            try {
                fileChannel.position(currentP);
                fileChannel.write(ByteBuffer.wrap(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
            wrotePosition.addAndGet(data.length);
            return true;
        }
        return false;
    }

    public int commit(final int commitLeastPages) {
        if (fileChannel == null) {
            return wrotePosition.get();
        }
        if (this.isAbleToCommit(commitLeastPages)) {
            doCommit();
        }

        return committedPosition.get();
    }

    private void doCommit() {
        int writePos = wrotePosition.get();
        int lastCommitPos = committedPosition.get();
        if (writePos - lastCommitPos > 0) {
            ByteBuffer byteBuffer = writeBuffer.slice();
            byteBuffer.position(lastCommitPos);
            byteBuffer.limit(writePos);
            try {
                fileChannel.position(lastCommitPos);
                fileChannel.write(byteBuffer);
                committedPosition.set(writePos);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public int flush(final int flushLeastPages) {
        if (this.isAbleToFlush(flushLeastPages)) {
            int value = getReadPosition();

            try {
                //We only append data to fileChannel or mappedByteBuffer, never both.
                if (writeBuffer != null || this.fileChannel.position() != 0) {
                    // 写消息到broker本地文件中
                    this.fileChannel.force(false);
                } else {
                    this.mappedByteBuffer.force();
                }
            } catch (Throwable e) {
                System.err.printf("Error occurred when force data to disk.e:%s", e);
            }

            // 更新刷盘指针
            this.flushedPosition.set(value);
        }
        return flushedPosition.get();
    }


    protected boolean isAbleToCommit(final int commitLeastPages) {
        int flush = this.committedPosition.get();
        int write = this.wrotePosition.get();

        if (this.isFull()) {
            return true;
        }

        if (commitLeastPages > 0) {
            return ((write / OS_PAGE_SIZE) - (flush / OS_PAGE_SIZE)) >= commitLeastPages;
        }

        return write > flush;
    }

    public static String getFileContent(String fileName) {
        // mmap
        try (RandomAccessFile file = new RandomAccessFile(fileName, "r")) {
            FileChannel channel = file.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(fileSize);
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

    public boolean isFull() {
        return this.fileSize == this.wrotePosition.get();
    }

    private boolean isAbleToFlush(final int flushLeastPages) {
        int flush = this.flushedPosition.get();
        int write = getReadPosition();

        // 文件满了
        if (this.isFull()) {
            return true;
        }

        if (flushLeastPages > 0) {
            return ((write / OS_PAGE_SIZE) - (flush / OS_PAGE_SIZE)) >= flushLeastPages;
        }

        return write > flush;
    }

    public int getReadPosition() {
        return this.writeBuffer == null ? this.wrotePosition.get() : this.committedPosition.get();
    }

    public ByteBuffer selectMappedBuffer(int pos) {
        int readPosition = getReadPosition();
        if (pos < readPosition && pos >= 0) {
            ByteBuffer byteBuffer = this.mappedByteBuffer.slice();
            byteBuffer.position(pos);
            int size = readPosition - pos;
            ByteBuffer byteBufferNew = byteBuffer.slice();
            byteBufferNew.limit(size);
            return byteBufferNew;
        }

        return null;
    }

    public String selectString(int pos){
        ByteBuffer byteBuffer = selectMappedBuffer(pos);
        if(byteBuffer!=null){
            byte[] data=new byte[1024];
            byteBuffer.get(data);
            return new String(data);
        }
        return null;
    }
}
