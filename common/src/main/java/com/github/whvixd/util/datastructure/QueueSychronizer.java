package com.github.whvixd.util.datastructure;

/**
 * Created by wangzhx on 2020/4/24.
 */
public class QueueSychronizer {

    private volatile int state;

    class Node{
        Node next;
        Node prev;
        Thread thread;
    }


}
