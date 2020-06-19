package com.github.whvixd.demo.algorithm.leetcode.hard;

import com.github.whvixd.demo.algorithm.leetcode.TreeNode;

/**
 我们从二叉树的根节点 root 开始进行深度优先搜索。

 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。

 如果节点只有一个子节点，那么保证该子节点为左子节点。

 给出遍历输出 S，还原树并返回其根节点 root。

  

 示例 1：



 输入："1-2--3--4-5--6--7"
 输出：[1,2,5,3,4,6,7]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhixiang on 2020/6/18.
 */
public enum Q1028 {

    instance;

    public TreeNode recoverFromPreorder(String S) {
        if(S==null||S.length()==0)return null;
        int rootVal=Integer.valueOf(S.charAt(0)+"");
        TreeNode root=new TreeNode(rootVal);

        int level=1;
        for(int i=1;i<S.length();i++){
            if(S.charAt(i)=='-'){
                level++;
            }else {

            }
        }
        return null;
    }
}
