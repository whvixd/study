package com.github.whvixd.demo.algorithmDemo.leetcode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by wangzhx on 2020/3/25.
 */
public enum Q8 {
    instance;

    public ListNode swapPairs(ListNode head) {
        ListNode newHead = new ListNode(0);
        ListNode p=head,q;
        ListNode a = newHead;
        while (true){
            if(p==null){
                return newHead.next;
            }
            else {
                q = p.next;
                if(q==null){
                    a.next=p;
                    return newHead.next;
                }else {
                    ListNode p1 = q.next;
                    if(p1!=null){
                        a.next=q;
                        q.next=null;
                        a=a.next;
                        a.next=p;
                        p.next=null;
                        a=a.next;
                        p=p1;
                    }else {
                        a.next=q;
                        a=a.next;
                        a.next=p;
                        p.next=null;
                        return newHead.next;
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next = new ListNode(10);
        ListNode.print(Q8.instance.swapPairs(head));
    }
}
