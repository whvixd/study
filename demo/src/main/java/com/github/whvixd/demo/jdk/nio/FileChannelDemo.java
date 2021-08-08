package com.github.whvixd.demo.jdk.nio;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileMode;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangzhx on 2019/8/13.
 */
public class FileChannelDemo {
    private MappedByteBuffer mappedByteBuffer;
    private String fileName;
    private File file;
    private FileChannel fileChannel;
    // 推外内存
    private ByteBuffer writeBuffer;
    // 64k
    private  int fileSize = 1024 * 64;
    private int commitCommitLogLeastPages = 4;
    private int flushCommitLogLeastPages = 4;

    private static final int OS_PAGE_SIZE = 3;

    // 写指针
    private final AtomicInteger wrotePosition = new AtomicInteger(0);
    // 提交指针
    private final AtomicInteger committedPosition = new AtomicInteger(0);
    // 刷盘指针
    private final AtomicInteger flushedPosition = new AtomicInteger(0);


    public void init(final String fileName,int fileSize) throws IOException {
        this.fileName = fileName;
        this.file = new File(fileName);
        FileUtil.mkdir(file.getParent());
        this.fileChannel = FileUtil.createRandomAccessFile(this.file, FileMode.rw).getChannel();
        // mmap
        this.mappedByteBuffer = this.fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
        this.writeBuffer = ByteBuffer.allocateDirect(16);
    }
    public void init(final String fileName) throws IOException {
        this.init(fileName,1024 * 64);
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

    // 先写到推外内存，再提交，最后刷盘，存到磁盘中
    public boolean write(final byte[] data) {
        int currentP = wrotePosition.get();
        if (data.length + currentP <= fileSize) {
            // 移到写的位置
            writeBuffer.position(currentP);
            // put后，position+=data.length
            writeBuffer.put(data);
            wrotePosition.addAndGet(data.length);
            return true;
        }
        return false;
    }

    // 先写到推外内存，再提交，最后刷盘，存到磁盘中
    public boolean write0(final byte[] data) {
        int currentP = wrotePosition.get();
        if (data.length + currentP <= fileSize) {
            ByteBuffer slice = writeBuffer.slice();
            // 移到写的位置
            slice.position(currentP);
            // put后，position+=data.length
            slice.put(data);
            wrotePosition.addAndGet(data.length);
            return true;
        }
        return false;
    }

    public int commit(final int commitLeastPages) {
        if (writeBuffer == null) {
            return wrotePosition.get();
        }
        if (this.isAbleToCommit(commitLeastPages)) {
            doCommit();
        }

        return committedPosition.get();
    }
    public int commit0(final int commitLeastPages) {
        if (writeBuffer == null) {
            return wrotePosition.get();
        }
        if (this.isAbleToCommit(commitLeastPages)) {
            doCommit0();
        }

        return committedPosition.get();
    }

    private void doCommit() {
        int writePos = wrotePosition.get();
        int lastCommitPos = committedPosition.get();
        if (writePos - lastCommitPos > 0) {
            // 移到上次提交位置
            writeBuffer.position(lastCommitPos);
            // 截取的起始位置是writeBuffer.position位置
            ByteBuffer byteBuffer = writeBuffer.slice();
            byteBuffer.limit(writePos-lastCommitPos);
            try {
                this.fileChannel.position(lastCommitPos);
                this.fileChannel.write(byteBuffer);
                this.committedPosition.set(writePos);
            } catch (IOException e) {
                System.err.printf("Error occurred when doCommit message to mappedFile.e:%s", e);
            }

        }
    }

    private void doCommit0() {
        int writePos = wrotePosition.get();
        int lastCommitPos = committedPosition.get();
        if (writePos - lastCommitPos > 0) {
            ByteBuffer byteBuffer = writeBuffer.slice();
            // 移到上次提交位置
            byteBuffer.position(lastCommitPos);
            byteBuffer.limit(writePos);
            try {
                this.fileChannel.position(lastCommitPos);
                this.fileChannel.write(byteBuffer);
                this.committedPosition.set(writePos);
            } catch (IOException e) {
                System.err.printf("Error occurred when doCommit message to mappedFile.e:%s", e);
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

    public String selectString(int pos,int length){
        ByteBuffer byteBuffer = selectMappedBuffer(pos);
        if(byteBuffer!=null){
            byte[] data=new byte[length];
            byteBuffer.get(data);
            return new String(data);
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        FileChannelDemo fileChannelUtil=new FileChannelDemo();
        fileChannelUtil.init("/Users/didi/Documents/workspace/idea/study/demo/src/main/java/com/github/whvixd/demo/jdk/nio/test_mmap");
        String in="abcd";
        // 写到堆外缓存
        fileChannelUtil.write0(in.getBytes());
        // 提交，已经写入文件
        fileChannelUtil.commit0(0);
        // 刷盘
        fileChannelUtil.flush(0);

        String a="efg";
        fileChannelUtil.write0(a.getBytes());
        fileChannelUtil.commit0(0);
        fileChannelUtil.flush(0);

        String s = fileChannelUtil.selectString(0, (in+a).length());
        System.out.println(s);
    }
}
