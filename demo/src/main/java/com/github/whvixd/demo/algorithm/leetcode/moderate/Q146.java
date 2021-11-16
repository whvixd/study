package com.github.whvixd.demo.algorithm.leetcode.moderate;

import cn.hutool.core.util.RandomUtil;
import com.github.whvixd.demo.algorithm.leetcode.LinkedNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

/**
 *
 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 写入数据 put(key, value) - 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/lru-cache
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public enum Q146 {
    instance;

    static class LRUCache {
        // save k and v
        LinkedNode head;
        LinkedNode tail;

        // save k and LinkedNode's point
        Map<Integer,LinkedNode> map;
        int capacity;

        // Linked's size
        int length;

        public LRUCache(int capacity) {
            if(capacity<=0) throw new Error("capacity is illegal");
            this.capacity=capacity;
            this.map=new HashMap<>(capacity);
            this.head=new LinkedNode();
            this.tail=new LinkedNode();
            // init head and tail
            head.next=tail;
            head.prev=null;
            tail.prev=head;
            tail.next=null;
        }

        public int get(int key) {
            if(map.containsKey(key)){
                LinkedNode node = map.get(key);
                moveToHead(node);
                return node.val;
            }
            return -1;
        }

        private void moveToHead(LinkedNode node) {
            if(node==null||node.prev==head) return;
            LinkedNode prevNode=node.prev;
            prevNode.next=node.next;
            node.next.prev=prevNode;
            node.next=head.next;
            node.prev=head;
            head.next.prev=node;
            head.next=node;
        }

        public void put(int key, int value) {
            if(map.containsKey(key)){
                LinkedNode node = map.get(key);
                node.val=value;
                moveToHead(node);
                return;
            }
            if(length>=capacity){
                LinkedNode removedNode = removeLast();
                if(removedNode!=null){
                    map.remove(removedNode.key);
                }
            }
            map.put(key,addFist(key,value));
        }

        private LinkedNode addFist(int key,int value){
            LinkedNode newNode=new LinkedNode(key,value);
            newNode.next=head.next;
            newNode.prev=head;
            head.next.prev=newNode;
            head.next=newNode;
            length++;
            return newNode;
        }

        private LinkedNode removeLast(){
            if(tail==null||tail.prev==null) return null;
            LinkedNode node=tail.prev.prev;
            if(node!=null){
                LinkedNode removeNode=tail.prev;
               node.next=tail;
               tail.prev=node;
               length--;
               return removeNode;
            }
            return null;
        }

        /**
         * Your LRUCache object will be instantiated and called as such:
         * LRUCache obj = new LRUCache(capacity);
         * int param_1 = obj.get(key);
         * obj.put(key,value);
         */
    }

    private void test1(){
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.put(3,3);
        System.out.println(lruCache.get(1));
        // remove 2
        lruCache.put(4,4);
        // -1
        System.out.println(lruCache.get(2));

        lruCache.put(3,6);
        // 6
        System.out.println(lruCache.get(3));
    }

    private void test2(){
        // 4->2
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2,1);
        lruCache.put(1,1);
        lruCache.put(2,3);
        lruCache.put(4,1);
        // -1
        System.out.println(lruCache.get(1));
        // 3
        System.out.println(lruCache.get(2));
    }

    private void testPerformance(){
        int size=100000;
        // 初始化一万的容量，并添加
        LRUCache lruCache = new LRUCache(size);
        long putStart = System.currentTimeMillis();
        IntStream.range(0,size).forEach(i->{
            lruCache.put(i,i);
        });
        long putEnd = System.currentTimeMillis();
        System.out.println(lruCache.length);
        long getStart = System.currentTimeMillis();
        IntStream.range(0,size).forEach(i->{
            lruCache.get(RandomUtil.randomInt(size));
        });
        long getEnd = System.currentTimeMillis();

        System.out.println("put:"+(putEnd-putStart)+"ms");
        System.out.println("get:"+(getEnd-getStart)+"ms");
    }

    public static void main(String[] args) {
        Q146.instance.testPerformance();
    }
}
