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
    private ThreadLocal<Node> cur;
    /**
     * 前置节点
     */
    private ThreadLocal<Node> prev;

    public CLHLock(){
        // 初始化尾节点
        tail = new AtomicReference<>(new Node());
        // 初始化节点
        cur = ThreadLocal.withInitial(Node::new);
        // 初始化前置节点，值为空
        prev = ThreadLocal.withInitial(() -> null);

    }

    @Override
    public void lock() {
        // 加锁过程

        // get():若Node为空，则新建Node；再锁定
        cur.get().locked=true;
        // getAndSet()方法修改尾节点，返回tail修改前的值
        Node prev = tail.getAndSet(cur.get());
        // 设置前置
        this.prev.set(prev);
        // 当前线程自旋(堵塞)，依赖前置线程的cur，一直到前置线程解锁，才跳出自旋
        for (;prev.locked;);
    }

    @Override
    public void unlock() {
        // 解锁
        cur.get().locked=false;
        // 前置赋值给当前节点
        cur.set(prev.get());
        // help gc prev node
        prev.remove();
    }

    class Node{
        volatile boolean locked;
//        Node(){
//            System.out.println(Thread.currentThread().getName()+" ,Node init");
//        }
    }
}
