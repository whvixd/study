package com.github.whvixd.demo.algorithm.leetcode.easy;

import com.github.whvixd.demo.algorithm.leetcode.ListNode;
import com.github.whvixd.util.SystemUtil;

/**
 * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。

 请你返回该链表所表示数字的 十进制值 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by wangzhx on 2020/5/26.
 */
public enum Q1290 {
    instance;
    public int getDecimalValue(ListNode head) {
        // 确认链表的长度
        int l=0;
        ListNode point=head;
        while (point!=null){
            l++;
            point=point.next;
        }
        int sum=0;
        while (head!=null){
            l--;
            sum+=head.val*(Math.pow(2,l));
            head=head.next;
        }
        return sum;
    }
    public int getDecimalValue1(ListNode head) {
        int sum=0;
        while (head!=null){
            sum=sum*2+head.val;
            head=head.next;
        }
        return sum;
    }

    public static void main(String[] args) {
        //assert 18880;
        ListNode head = ListNode.createByArray(new int[]{1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0});
        SystemUtil.print(Q1290.instance.getDecimalValue1(head));
    }

}
