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

    public static void print(ListNode l) {
        ListNode head = l;
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
            if (head != null) {
                System.out.print("->");
            }
        }

    }
}
