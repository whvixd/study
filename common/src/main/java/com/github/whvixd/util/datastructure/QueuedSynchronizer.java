package com.github.whvixd.util.datastructure;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by wangzhx on 2020/4/24.
 */
public class QueuedSynchronizer implements Lock{

    private transient Thread exclusiveOwnerThread;
    private volatile int state;
    private transient volatile Node head;
    private transient volatile Node tail;

    public QueuedSynchronizer(){}

    @Override
    public void lock() {
        // 谁先cas修改state成功，获取锁资源
        if(compareAndSetState(0,1)){
            setExclusiveOwnerThread(Thread.currentThread());
        }else {
            // 未修改成功，再次获取，若没有成功，则添加队列中，并堵塞
            acquire(1);
        }
    }

    @Override
    public void unlock() {
        release(1);
    }


    private void acquire(int state){
        // 再次获取锁资源
        if(!tryAcquire(state)){
            // 没有获取成功的线程，进入堵塞队列，并修改线程状态
            addWaiter();
            LockSupport.park(this);
            // 线程堵塞，解锁后，需要获取锁资源
            for(;!tryAcquire(state););
        }
        System.out.println("tryAcquire is true");
    }

    private void release(int expectState) {
        //// todo 解锁有问题
        if(tryRelease(expectState)){
            System.out.println(Thread.currentThread().getName()+" skip tryRelease");
            if(head==null){
                System.out.println("head is null");
                return;
            }
            LockSupport.unpark(head.getThread());
            Node next = head.getNext();
            if(next!=tail&&next!=null){
                setHead(next);
                next.setPrev(null);
            }else {
                setHead(tail);
                System.out.println(Thread.currentThread().getName()+" setHead(tail);");
            }
        }

    }

    private void checkAndRemove(Node head) {
        Node point=head;
        for(;;){
            if(point==null){
                return;
            }
            Thread thread = point.getThread();
            if(point.getThread()==null||!thread.isInterrupted()){
                if(point.getPrev()!=null&&point.getNext()!=null){
                    point.getPrev().setNext(point.getNext());
                    point.getNext().setPrev(point.getPrev());
                }
            }
            point=point.getNext();
        }

    }

    private boolean tryRelease(int expectState){
        int state=getState();
        boolean released=false;
        for(;;){
            System.out.println(Thread.currentThread().getName()+"tryRelease loop");
            if(state<=0){
                released=true;
            }
            else if(state==1){
                int newState=state-expectState;
                if(compareAndSetState(state,newState)){
                    released=true;
                }
            }else {
                int newState=state-expectState;
                if(compareAndSetState(state,newState)){
                    released=true;
                }
            }

            if(released){
                break;
            }
        }
        return true;
    }



    private Node addWaiter(){
        Thread currentThread = Thread.currentThread();
        Node newNode = new Node(currentThread);
        Node tail=getTail();
        Node head=getHead();
        if(head==null&&tail==null){
            boolean added=false;
            for(;;){
                System.out.println("set head and tail start");
                if(compareAndSetHead(getHead(),newNode)&&compareAndSetTail(getTail(),newNode)){
                    System.out.println("head :"+getHead().getThread().getName());
                    System.out.println("tail :"+getTail().getThread().getName());
                    added=true;
                }
                if(added){
                    break;
                }
            }
            return newNode;
        }else {
            for(;;){
                Node prev=tail;
                if(compareAndSetTail(tail,newNode)){
                    newNode.prev=prev;
                    prev.next=newNode;
                    return newNode;
                }
            }
        }
    }

    private boolean tryAcquire(int expectState){
        int state = getState();
        // 如果是当前线程获取资源
        if(state==0){
            if(compareAndSetState(0,expectState)){
                setExclusiveOwnerThread(getExclusiveOwnerThread());
                return true;
            }
        }
        else if(isCurrentThread()){
            int nextState=state+expectState;
            if(nextState<0){
                throw new Error("nextState is illegal");
            }
            setState(nextState);
            return true;
        }
        return false;
    }



    private boolean isCurrentThread(){
        return getExclusiveOwnerThread()!=null&&getExclusiveOwnerThread()==Thread.currentThread();
    }

    public Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }

    public void setExclusiveOwnerThread(Thread exclusiveOwnerThread) {
        this.exclusiveOwnerThread = exclusiveOwnerThread;
    }

    public final int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    class Node{
        Node next;
        Node prev;
        Thread thread;

        Node(Thread thread){
            this.thread=thread;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Thread getThread() {
            return thread;
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }
    }

    private static final Unsafe unsafe = getUnsafeInstance();
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;

    private static Unsafe getUnsafeInstance() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new Error("get unsafe error", e);
        }
    }

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(QueuedSynchronizer.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset(QueuedSynchronizer.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset(QueuedSynchronizer.class.getDeclaredField("tail"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }

    private final boolean compareAndSetState(int expect, int update){
        return unsafe.compareAndSwapInt(this,stateOffset,expect,update);
    }

    private final boolean compareAndSetHead(Node expect,Node update){
        return unsafe.compareAndSwapObject(this,headOffset,expect,update);
    }

    private final boolean compareAndSetTail(Node expect,Node update){
        return unsafe.compareAndSwapObject(this,tailOffset,expect,update);
    }


}
