package com.github.whvixd.demo.algorithm.leetcode.moderate;

import com.github.whvixd.demo.algorithm.leetcode.ListNode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

 示例：

 给定一个链表: 1->2->3->4->5, 和 n = 2.

 当删除了倒数第二个节点后，链表变为 1->2->3->5.

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 todo 优化，用两个指针，间隔为n
 * Created by wangzhx on 2020/3/23.
 */
public enum Q19 {
    instance;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0) {
            return head;
        }

        ListNode c = head;
        int length = 0;
        while (c != null) {
            length++;
            c = c.next;
        }
        if(length<n){
            return head;
        }

        int point = length - n;
        int a = 0;
        ListNode l = head;
        while (l != null) {
            if (point == 0) {
                return l.next;
            }
            a++;
            if (a == point) {
                l.next = l.next.next;
                break;
            }
            l = l.next;

        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        ListNode listNode = Q19.instance.removeNthFromEnd(head, 3);
        ListNode.print(listNode);
    }
}
