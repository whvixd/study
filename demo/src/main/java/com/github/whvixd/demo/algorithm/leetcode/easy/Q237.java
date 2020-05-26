package com.github.whvixd.demo.algorithm.leetcode.easy;

import com.github.whvixd.demo.algorithm.leetcode.ListNode;

/**
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。

 现有一个链表 -- head = [4,5,1,9]，它可以表示为:

 4->5->1->9

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by wangzhx on 2020/5/26.
 */
public enum Q237 {
    istance;

    // as same as @see Q0203
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
        int[] array=new int[]{4,5,1,9};
        ListNode node = ListNode.createByArray(array);
        ListNode.print("before:",node);
        Q0203.instance.deleteNode(node.next.next);
        ListNode.print("after:",node);
    }
}
