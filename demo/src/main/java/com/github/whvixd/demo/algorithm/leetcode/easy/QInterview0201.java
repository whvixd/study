package com.github.whvixd.demo.algorithm.leetcode.easy;

import com.github.whvixd.demo.algorithm.leetcode.ListNode;

/**
 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。

 示例1:

 输入：[1, 2, 3, 3, 2, 1]
 输出：[1, 2, 3]
 示例2:

 输入：[1, 1, 1, 1, 2]
 输出：[1, 2]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/remove-duplicate-node-lcci
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhixiang on 2020/6/26.
 */
public enum QInterview0201 {
    instance;
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode p=head;
        while (p!=null){
            ListNode r=p;
            while (r.next!=null){
                if(p.val==r.next.val){
                    // 删除重复节点
                    r.next=r.next.next;
                    continue;
                }
                r=r.next;
            }
            p=p.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = ListNode.createByArray(new int[]{1, 2, 3, 3, 2, 1});
        QInterview0201.instance.removeDuplicateNodes(node1);
        // assert [1, 2, 3]
        ListNode.print(node1);

        ListNode node2 = ListNode.createByArray(new int[]{1, 1, 1, 1, 2});
        QInterview0201.instance.removeDuplicateNodes(node2);
        // assert [1, 2]
        ListNode.print(node2);
    }

}
