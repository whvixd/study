package com.github.whvixd.demo.algorithm.leetcode.easy;

import java.util.*;

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
        // 存储v
        // todo 换为手写单链表
        LinkedList<Integer> list;
        // todo 存储单链表的指针
        Map<Integer,Integer> map;

        int capacity;

        public LRUCache(int capacity) {
            if(capacity<=0) throw new Error("capacity is illegal");
            this.capacity=capacity;
            this.list=new LinkedList<>();
            this.map = new HashMap<>(capacity);
        }

        public int get(int key) {

            // todo 从map中获取单链表的指针，再将指针的节点移到头结点上
            return 1;
        }

        public void put(int key, int value) {
            if(list.size()<capacity){
                list.addFirst(value);
            }else {
                list.removeLast();
                list.addFirst(value);
            }
            map.put(key, list.indexOf(value));
        }

        /**
         * Your LRUCache object will be instantiated and called as such:
         * LRUCache obj = new LRUCache(capacity);
         * int param_1 = obj.get(key);
         * obj.put(key,value);
         */
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);

    }
}
