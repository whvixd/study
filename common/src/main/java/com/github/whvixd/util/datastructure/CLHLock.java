package com.github.whvixd.util.datastructure;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 线程少的情况下可以
 * Created by wangzhx on 2020/4/24.
 */
public class CLHLock implements Lock {
    private AtomicReference<Node> tail;
    private ThreadLocal<Node> node;
    private ThreadLocal<Node> prev;

    public CLHLock(){
        tail = new AtomicReference<>(new Node());
        node = ThreadLocal.withInitial(Node::new);
        prev = ThreadLocal.withInitial(() -> null);

    }
    @Override
    public void lock() {
        Node node = this.node.get();
        node.state=true;
        // 旧值
        Node prev = tail.getAndSet(node);
        this.prev.set(prev);
        while (prev.state){
            System.out.println("wait");
        }
    }

    @Override
    public void unlock() {
        Node node = this.node.get();
        node.state=false;
        this.node.set(this.prev.get());
    }

    class Node{
        volatile boolean state;
    }
}
