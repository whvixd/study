package com.github.whvixd.demo.algorithm.leetcode;

/**
 * Created by wangzhx on 2020/3/21.
 */
public class ListNode {
    public  int val;
    public ListNode next;

    public ListNode(){}

    public ListNode(int x) {
        val = x;
    }

    public static void print(ListNode l, String preMessage, String postMessage) {
        preMessage=preMessage==null?"":preMessage;
        postMessage=postMessage==null?"":postMessage;
        ListNode head = l;
        StringBuilder sb = new StringBuilder();
        sb.append(preMessage);
        while (head != null) {
            sb.append(head.val);
            head = head.next;
            if (head != null) {
                sb.append("->");
            }
        }
        sb.append(postMessage);
        System.out.println(sb);
    }

    public static void print(ListNode l) {
        print(l, null, null);
    }

    public static void print(String preMessage, ListNode l) {
        print(l, preMessage, null);
    }

    public static void print(ListNode l, String postMessage) {
        print(l, null, postMessage);
    }

    public static ListNode createByArray(int[] array){
        if(array.length==0) return null;
        ListNode head =new ListNode(0);
        ListNode point=head;
        for(int e:array){
            point=point.next=new ListNode(e);
        }
        return head.next;
    }
}
