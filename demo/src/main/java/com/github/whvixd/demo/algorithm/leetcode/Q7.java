package com.github.whvixd.demo.algorithm.leetcode;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Created by wangzhx on 2020/3/23.
 */
public enum Q7 {
    instance;

    public ListNode mergeKLists(ListNode[] lists) {
        int length = lists.length;
        if (length == 0) {
            return null;
        }
        ListNode head = new ListNode(0);

        for (ListNode node : lists) {
            if (node == null) {
                continue;
            }
            ListNode p = node;
            ListNode pNext;
            while (p != null) {
                ListNode q = head;
                while (true) {
                    if (q.next != null) {
                        if (q.next.val > p.val) {
                            pNext = p.next;
                            p.next = q.next;
                            q.next = p;
                            p = pNext;
                            break;
                        } else {
                            q = q.next;
                        }

                    } else {
                        pNext = p.next;
                        q.next = p;
                        p.next = null;
                        p = pNext;
                        break;
                    }
                }

            }

        }
        return head.next;

    }

    public static void main(String[] args) {
        ListNode h1 = new ListNode(1);
        h1.next = new ListNode(3);
        h1.next.next = new ListNode(5);

        ListNode h2 = new ListNode(1);
        h2.next = new ListNode(4);
        h2.next.next = new ListNode(6);

        ListNode h3 = new ListNode(1);
        h3.next = new ListNode(4);
        h3.next.next = new ListNode(6);

        ListNode[] listNodes = new ListNode[10];
        listNodes[0] = h1;
        listNodes[1] = h2;
        listNodes[2] = h3;
        ListNode.print("h1:",h1);
        ListNode.print("h2:",h2);
        ListNode.print("result:",Q7.instance.mergeKLists(listNodes));
    }
}
