package com.github.whvixd.demo.algorithmDemo.leetcode;

/**
 * Created by wangzhx on 2020/3/21.
 */
public class ListNode {
     int val;
    ListNode next;

    ListNode(int x) {
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
}
