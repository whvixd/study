package com.github.whvixd.demo.algorithm.leetcode;

/**
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * Created by wangzhx on 2020/3/21.
 */
public enum Q2 {
    instance;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //当前节点
        ListNode p1 = l1;
        ListNode p2 = l2;

        //下一节点
        ListNode n1;
        ListNode n2;

        //是否需要进一
        boolean b = false;
        while (p1 != null && p2 != null) {

            n1 = p1.next;
            n2 = p2.next;

            //填充节点短的，节点值为0
            if (n1 == null && n2 != null) {
                p1.next = new ListNode(0);
            } else if (n1 != null && n2 == null) {
                p2.next = new ListNode(0);
            }

            int v1 = p1.val;
            int v2 = p2.val;
            int s1 = v1 + v2;
            if (b) {
                s1 += 1;
            }

            if (s1 >= 10) {
                s1 -= 10;
                b = true;
            } else {
                b = false;
            }

            p1.val = s1;

            if (n1 == null && n2 == null) {
                //最后两节点值的和大于10，新增一节点
                if (b) {
                    p1.next = new ListNode(1);
                    break;
                }
            }

            p1 = p1.next;
            p2 = p2.next;

        }
        return l1;
    }


    public static void main(String[] args) {
        int[] a1 = {2, 4, 3};
        int[] a2 = {5, 6, 4};
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(9);

        ListNode.print(Q2.instance.addTwoNumbers(l1, l2));
    }



}
