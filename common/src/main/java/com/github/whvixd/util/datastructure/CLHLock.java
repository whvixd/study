package com.github.whvixd.util.datastructure;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 线程少的情况下可以
 * Created by wangzhx on 2020/4/24.
 */
public class CLHLock implements Lock {
    private AtomicReference<Node> tail;
    /**
     * 当前节点
     */
    private ThreadLocal<Node> node;
    /**
     * 前置节点
     */
    private ThreadLocal<Node> prev;

    public CLHLock(){
        tail = new AtomicReference<>(new Node());
        node = ThreadLocal.withInitial(Node::new);
        prev = ThreadLocal.withInitial(() -> null);

    }
    // 线程一 进入，拿到node false，执行代码块，并将tail更新为true
    // 线程二进入，此时线程一没有执行完，拿到node true，等线程一执行完之后，更新node为false，更新到prev中，则线程一跳出自旋
    @Override
    public void lock() {
        node.get().state=true;
        Node prev = tail.getAndSet(node.get());
        this.prev.set(prev);
        for (;prev.state;);
    }

    @Override
    public void unlock() {
        node.get().state=false;
        node.set(prev.get());
    }

    class Node{
        volatile boolean state;
    }
}
