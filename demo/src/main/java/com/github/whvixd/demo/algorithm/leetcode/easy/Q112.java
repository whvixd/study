package com.github.whvixd.demo.algorithm.leetcode.easy;

import com.github.whvixd.demo.algorithm.leetcode.TreeNode;

/**
 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

 说明: 叶子节点是指没有子节点的节点。

 示例: 
 给定如下二叉树，以及目标和 sum = 22，

 5
 / \
 4   8
 /   / \
 11  13  4
 /  \      \
 7    2      1
 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/path-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangzhixiang on 2020/7/7.
 */
public enum Q112 {
    instance;
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null)return false;
        return pre(root,sum,root.val);
    }

    private boolean pre(TreeNode node,int sum,int current){
        if(node==null){
            return false;
        }
        if((node.left==null&&node.right==null)&&current==sum){
            return true;
        }
        boolean leftResult=false;
        if(node.left!=null){
            leftResult= pre(node.left,sum,current+node.left.val);
        }
        boolean rightResult=false;
        if(node.right!=null){
            rightResult= pre(node.right,sum,current+node.right.val);
        }
        return leftResult||rightResult;
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode( 5);
        root.left=new TreeNode(4);
        root.right=new TreeNode(8);

        root.left.left=new TreeNode(11);
        root.right.left=new TreeNode(13);
        root.right.right=new TreeNode(4);

        root.left.left.left=new TreeNode(7);
        root.left.left.right=new TreeNode(2);
        root.right.right.right=new TreeNode(1);
        // assert true
        System.out.println(Q112.instance.hasPathSum(root,22));

        TreeNode root1=new TreeNode( 1);
        root1.left=new TreeNode(2);
        // assert false
        System.out.println(Q112.instance.hasPathSum(root1,2));

        TreeNode root2=new TreeNode( -2);
        root2.right=new TreeNode(-3);
        // assert true
        System.out.println(Q112.instance.hasPathSum(root2,-5));
    }
}
