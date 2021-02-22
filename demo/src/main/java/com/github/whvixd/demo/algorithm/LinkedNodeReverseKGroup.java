package com.github.whvixd.demo.algorithm;

import com.github.whvixd.demo.algorithm.leetcode.ListNode;

/**
 * Created by wangzhixiang on 2021/2/22.
 */
public class LinkedNodeReverseKGroup {
    /**
     *
     * @param head ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode reverseKGroup (ListNode head, int k) {
        // write code here
        if (head == null || head.next == null || k == 1) return head;
        int t = k - 1;
        ListNode p0 = null, p1 = head, p2 = head;
        while (p2 != null) {
            if (t != 0) {
                p2 = p2.next;
                t--;
            }
            if (t == 0 && p2 != null) {
                reverse(p1, p2);

                if (p0 == null) {
                    head = p2;
                } else {
                    p0.next = p2;
                }
                p0 = p1;
                p1 = p1.next;
                p2 = p1;
                t = k - 1;
            }
        }
        return head;
    }
    public void reverse(ListNode head, ListNode tail) {
        ListNode p = head,p1 = head.next, last = tail.next;
        while (p1 != last && p1 != null) {
            head.next = p1.next;
            p1.next = p;
            p = p1;
            p1 = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = ListNode.createByArray(new int[]{1,2,3,4,5,6,7,8});
        LinkedNodeReverseKGroup group=new LinkedNodeReverseKGroup();
        ListNode listNode = group.reverseKGroup(head, 3);
        ListNode.print(listNode);
    }
}
