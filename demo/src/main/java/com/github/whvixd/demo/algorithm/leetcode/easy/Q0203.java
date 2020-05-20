package com.github.whvixd.demo.algorithm.leetcode.easy;

import com.github.whvixd.demo.algorithm.leetcode.ListNode;

/**
 * https://leetcode-cn.com/problems/delete-middle-node-lcci/
 *
 * Created by wangzhx on 2020/5/20.
 */
public enum Q0203 {
    instance;

    public void deleteNode(ListNode node) {
        if (node == null) return;
        while (node.next != null) {
            node.val = node.next.val;
            if (node.next.next != null) {
                node = node.next;
            } else {
                break;
            }
        }
        node.next = null;

    }

    public static void main(String[] args) {
        int[] array=new int[]{1,2,3,4,5};
        ListNode node = ListNode.createByArray(array);

//        ListNode head=new ListNode(1);
//        head.next=new ListNode(2);
//        head.next.next=new ListNode(3);
//        head.next.next.next=new ListNode(4);
        ListNode.print("before:",node);
        Q0203.instance.deleteNode(node.next.next);
        ListNode.print("after:",node);
    }
}
