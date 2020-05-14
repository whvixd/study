package com.github.whvixd.util.datastructure;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by wangzhx on 2020/5/14.
 */
public class OptionalCLHLock implements Lock{
    private AtomicReference<CLHNode> tail;
    private ThreadLocal<CLHNode> threadLocal;

    public OptionalCLHLock() {
        this.tail = new AtomicReference<>();
        this.threadLocal = new ThreadLocal<>();
    }

    @Override
    public void lock() {
        CLHNode curNode = threadLocal.get();
        if(curNode == null){
            curNode = new CLHNode();
            threadLocal.set(curNode);
        }

        CLHNode prevNode = tail.getAndSet(curNode);
        if(prevNode != null){
            while (prevNode.getLocked()){

            }
        }
    }

    @Override
    public void unlock() {
        CLHNode curNode = threadLocal.get();
        threadLocal.remove();

        if(curNode == null || curNode.getLocked() == false){
            return;
        }

        if(!tail.compareAndSet(curNode, null)){
            curNode.setLocked(false);
        }
    }

    class CLHNode {
        private volatile boolean locked = true;

        public boolean getLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }
    }
}
