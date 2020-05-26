package com.github.whvixd.util.datastructure;

/**
 * 最久未使用淘汰算法
 *
 * get时间负责度O(n),改为O(1) @see Q146
 *
 * Created by wangzhx on 2020/4/23.
 */
public class LRU<K,V> {
    private Node<K,V> head;
    private Node<K,V> tail;
    private int capacity=Integer.MAX_VALUE;

    private static class Node<K,V>{
        K key;
        V value;
        Node<K,V> next;
        Node<K,V> prev;

        public Node(){
        }

        public Node(K key,V value){
            this.key=key;
            this.value=value;
        }
    }

    public LRU(){
        init();
    }

    public LRU(int capacity){
        init();
        this.capacity=capacity;
    }

    public void init(){
        this.head = new Node<>();
        this.tail = new Node<>();
        head.next=tail;
        tail.prev=head;
    }

    public int size(){
        if(head!=null){
            Node<K,V> nodePoint = head.next;
            int size=0;
            while (nodePoint!=tail&&nodePoint!=null){
                size++;
                nodePoint=nodePoint.next;
            }
            return size;
        }
        return 0;
    }

    // 头插法
    public void set(K key,V value){
        Node<K,V> node = head.next;
        Node<K,V> newNode=new Node<>(key,value);

        newNode.next=node;
        newNode.prev=head;
        head.next=newNode;
        node.prev=newNode;

        if(size()>capacity){
            // 尾删除
            tailDeleteNode();
        }

    }

    public V get(K key){
        V value = null;
        if(size()>0){
            Node<K,V> pointNode = head.next;
            while (pointNode!=null&&pointNode!=tail){
                if(pointNode.key==key){
                   value=pointNode.value;
                   break;
                }
                pointNode=pointNode.next;
            }
            // 移到头部
            moveToHead(pointNode);
        }
        return value;
    }

    public void moveToHead(Node<K,V> pointNode){
        if(pointNode!=null&&pointNode!=tail&&pointNode.prev!=head){
            pointNode.prev.next=pointNode.next;
            pointNode.next.prev=pointNode.prev;
            pointNode.next=head.next;
            pointNode.prev=head;
            head.next.prev=pointNode;
            head.next=pointNode;
        }
    }

    public Node<K,V> tailDeleteNode(){
        if(size()>0){
            Node<K, V> deleteNode = tail.prev;
            deleteNode.prev.next=tail;
            tail.prev=deleteNode.prev;
            return deleteNode;
        }
        return null;
    }

    public void print(){
        if(size()!=0){
            Node<K,V> pointNode= head.next;
            while (pointNode!=tail){
                System.out.printf("(k:%s,v:%s)",String.valueOf(pointNode.key),String.valueOf(pointNode.value));
                pointNode=pointNode.next;
                if(pointNode!=tail){
                    System.out.print("->");
                }
            }
        }

    }
}
