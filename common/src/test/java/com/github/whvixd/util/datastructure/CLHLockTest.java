package com.github.whvixd.util.datastructure;

import java.util.stream.IntStream;

/**
 * Created by wangzhx on 2020/4/24.
 */
public class CLHLockTest {
    private final CLHLock clhLock = new CLHLock();
    private final OptionalCLHLock lock = new OptionalCLHLock();
    private int i = 0;

    private void testLock() {
        clhLock.lock();
        try {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            System.out.println(Thread.currentThread().getName() + ":" + i);
        } finally {
            clhLock.unlock();
        }
    }

    private void testOptionalLock() {
        lock.lock();
        try {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            System.out.println(Thread.currentThread().getName() + ":" + i);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        CLHLockTest lock = new CLHLockTest();
        IntStream.range(0, 100).forEach(e -> new Thread(lock::testLock).start());
    }
}
