package com.github.whvixd.demo.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzhx on 2021/2/23.
 */
public class LRUDemo {
    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        // write code here

        int resultLength = 0;
        for (int[] operator : operators) {
            if (operator[0] == 2) {
                resultLength++;
            }
        }

        LRUCache lru=new LRUCache(k);
        int[] r=new int[resultLength];
        int z=0;
        for(int[] e:operators){
            int op=e[0];
            if(op==1){
                lru.set(e[1],e[2]);
            }else if(op==2){
                r[z++]=lru.get(e[1]);
            }
        }
        return r;
    }



    static class LRUCache{
        LinkedNode head;
        LinkedNode tail;
        Map<Integer,LinkedNode> map;
        int capacity;
        int length;

        LRUCache(int capacity){
            if(capacity<=0){
                return;
            }
            this.capacity=capacity;
            this.map=new HashMap<>();
            this.head=new LinkedNode();
            this.tail=new LinkedNode();
            head.next=tail;
            head.prev=null;
            tail.prev=head;
            tail.next=null;

        }

        void set(int key,int val){
            // 若map中有key，则更新map
            if(map.containsKey(key)){
                LinkedNode node=map.get(key);
                node.val=val;
                // 移到队头
                mvToHead(node);
                return;
            }

            // 若溢出，则删除队尾节点
            if(length>=capacity){
                if(tail==null||tail.prev==null){
                    return;
                }
                LinkedNode node=tail.prev.prev;
                if(node!=null){
                    LinkedNode rmNode=tail.prev;
                    node.next=tail;
                    tail.prev=node;
                    length--;
                    map.remove(rmNode.key);
                }

            }
            // 若新节点，添加队头
            LinkedNode newNode=new LinkedNode(key,val);
            newNode.next=head.next;
            newNode.prev=head;
            head.next=newNode;
            newNode.next.prev=newNode;
            map.put(key,newNode);
            length++;
        }

        int get(int key){
            LinkedNode node=map.get(key);
            if(node==null){
                return -1;
            }
            // 移到队头
            mvToHead(node);
            return node.val;
        }
        void mvToHead(LinkedNode node){
            if(node==null||node.prev==head) return;
            node.prev.next=node.next;
            node.next.prev=node.prev;
            node.next=head.next;
            node.prev=head;
            node.next.prev=node;
            node.prev.next=node;
        }

    }

    static class LinkedNode{
        int key;
        int val;
        // 前
        LinkedNode prev;
        // 后
        LinkedNode next;
        LinkedNode(int key,int val){
            this.key=key;
            this.val=val;
        }
        LinkedNode(){}

    }

    public static void main(String[] args) {
        LRUDemo demo=new LRUDemo();
        // [[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
        int[][] p=new int[6][];
        p[0]=new int[]{1,1,1};
        p[1]=new int[]{1,2,2};
        p[2]=new int[]{1,3,2};
        p[3]=new int[]{2,1};
        p[4]=new int[]{1,4,4};
        p[5]=new int[]{2,2};
        int[] lru = demo.LRU(p, 3);

        System.out.println(Arrays.toString(lru));
    }
}
