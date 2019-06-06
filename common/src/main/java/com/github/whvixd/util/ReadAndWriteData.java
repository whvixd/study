package com.github.whvixd.util;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wangzhx on 2018/4/21.
 */
public class ReadAndWriteData {
    private int defaultSize = 1 << 4;
    private int size;
    private char[] container;
    private final ReadWriteLock lock = new ReentrantReadWriteLock(); // 创建读写锁
    private final Lock readLock = lock.readLock();    // 获取读锁
    private final Lock writeLock = lock.writeLock();  // 获取写锁


    public ReadAndWriteData(int size) {
        if (size <= 0) {
            this.size = defaultSize;
        } else {
            this.size = size;
        }
        container = new char[this.size];
        initialValue();
    }

    public ReadAndWriteData() {
        container = new char[defaultSize];
        this.size = defaultSize;
        initialValue();
    }

    private void initialValue() {
        for (int p = 0; p < container.length; p++) {
            container[p] = '*';
        }
    }

    public void write(String data) {
        writeLock.lock();
        try {
            if (StringUtils.isNotBlank(data)) {
                char[] dataChar = data.toCharArray();
                int ponit = 0;
                for (char c : dataChar) {
                    container[ponit] = c;
                    if (ponit >= container.length - 1 || ponit >= dataChar.length - 1) {
                        break;
                    }
                    ponit++;
                }
            }
        } finally {
            writeLock.unlock();
        }

    }

    public synchronized String read() {
        readLock.lock();
        try {
            StringBuffer sb = new StringBuffer();
            for (char c : container) {
                sb.append(c);
            }
            return sb.toString();
        } finally {
            readLock.unlock();
        }
    }

}
