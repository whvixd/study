package com.github.whvixd.demo.algorithm.leetcode.easy;

import com.github.whvixd.demo.algorithm.leetcode.TreeNode;

/**
 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

 示例:

 给定有序数组: [-10,-3,0,5,9],

 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

 0
 / \
 -3   9
 /   /
 -10  5

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by wangzhixiang on 2020/7/3.
 */
public enum  Q108 {
    instance;
    public TreeNode sortedArrayToBST(int[] nums) {
        int l=nums.length;
        TreeNode root=new TreeNode(nums[l/2]);
        fillNode(nums,root,0,l-1);
        return root;
    }

    // TODO: 2020/7/3 有问题
    private void fillNode(int[] nums, TreeNode node, int l, int r){
        int mid=(l+r)/2;
        if(l<r&&mid>0){
//            mid=mid%2==0?mid:mid+1;
            if(node==null){
                node=new TreeNode(nums[mid]);
            }
            // l + (r - l) / 2
            int a=nums[(l+mid)/2];
            if(node.left==null&&node.val>=a){
                node.left=new TreeNode(a);
                fillNode(nums,node.left,l,mid-1);
            }

            int b=nums[(r+mid+1)/2];
            if(node.right==null&&node.val<=b){
                node.right=new TreeNode(b);
                fillNode(nums,node.right,mid+1,r);
            }
        }
    }

    public TreeNode sortedArrayToBST1(int[] nums) {
        // 左右等分建立左右子树，中间节点作为子树根节点，递归该过程
        return nums == null ? null : buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int m = l + (r - l) / 2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = buildTree(nums, l, m - 1);
        root.right = buildTree(nums, m + 1, r);
        return root;
    }



    public static void main(String[] args) {
        Q108.instance.sortedArrayToBST(new int[]{-10,-3,0,5,9});
        Q108.instance.sortedArrayToBST1(new int[]{-10,-3,0,5,9});
    }

}
