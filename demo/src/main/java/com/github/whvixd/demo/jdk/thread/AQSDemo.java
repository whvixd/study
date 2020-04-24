package com.github.whvixd.demo.jdk.thread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by wangzhx on 2020/3/15.
 */
public class AQSDemo extends AbstractQueuedSynchronizer {
    public static void main(String[] args) {
        AQSDemo lock = new AQSDemo();
    }
}
