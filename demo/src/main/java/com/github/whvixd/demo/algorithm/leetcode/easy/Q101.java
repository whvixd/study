package com.github.whvixd.demo.algorithm.leetcode.easy;

import com.github.whvixd.demo.algorithm.leetcode.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。

 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

 1
 / \
 2   2
 / \ / \
 3  4 4  3
  

 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

 1
 / \
 2   2
 \   \
 3    3

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/symmetric-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * created by wangzhx on 2020/05/31
 */
public enum  Q101 {

    instance;

    // 层级遍历,todo 有问题
    public boolean isSymmetric(TreeNode root) {
        if (root==null) return true;

        Queue<TreeNode> queue= new LinkedList<>();

        List<TreeNode> list = new ArrayList<>(1);
        list.add(root);
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode point=queue.poll();

            fill(point.left,queue,list);
            fill(point.right,queue,list);
        }
        return compare(list,height(root));
    }

    public void fill(TreeNode node,Queue<TreeNode> queue,List<TreeNode> list){
        if(node!=null){
            queue.add(node);
        }
        list.add(node);
    }

    private boolean compare(List<TreeNode> list,int height){
        for (int k=2;k<=height;k++){
            for(int i = (int) (Math.pow(2,k-1)-1), j = (int) (Math.pow(2,k)-2); i<=j; i++,j--){
                TreeNode left=list.get(i);
                TreeNode right=list.get(j);
                if((left==null&&right!=null)||(left!=null&&right==null)){
                    return false;
                }
                if(left!=null&&right!=null){
                    if(left.val!=right.val){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private int height(TreeNode root){
        if(root==null)return 0;
        int l=height(root.left);
        int r=height(root.right);
        return l>=r?l+1:r+1;
    }

   private int height2(TreeNode root) {
        int front = -1, rear = -1;
        int last = 0, level = 0;
       TreeNode[] queue = new TreeNode[100000];
        if (root == null) {
            return 0;
        }
        queue[++rear]=root;
       TreeNode p;
        while(front<rear){
            p=queue[++front];
            if(p.left!=null){
                queue[++rear]=p.left;
            }
            if(p.right!=null){
                queue[++rear]=p.right;
            }
            if(front==last){
                level++;
                last=rear;
            }
        }
        return level;
    }

    // 递归
    public boolean isSymmetric1(TreeNode root) {
        if(root==null) return true;
        return isSymmetric1(root.left,root.right);
    }

    private boolean isSymmetric1(TreeNode left, TreeNode right) {

        if(left!=null&&right!=null){
            if(left.val==right.val){
                return isSymmetric1(left.left,right.right)&&isSymmetric1(left.right,right.left);
            }else {
                return false;
            }
        }else if(left==null&&right==null){
            return true;
        }else {
            return false;
        }

    }


    public static void main(String[] args) {
        TreeNode root1=new TreeNode(1);
        root1.left=new TreeNode(2);
        root1.right=new TreeNode(2);
        root1.left.left=new TreeNode(3);
        root1.left.right=new TreeNode(4);
        root1.right.left=new TreeNode(4);
        root1.right.right=new TreeNode(3);
        // assert true
//        System.out.println(Q101.instance.isSymmetric1(root1));
//        System.out.println(Q101.instance.isSymmetric(root1));

        TreeNode root2=new TreeNode(3);
        root2.left=new TreeNode(4);
        root2.right=new TreeNode(4);
        root2.left.left=new TreeNode(5);
        root2.right.right=new TreeNode(5);
        root2.left.left.left=new TreeNode(6);
        root2.right.right.right=new TreeNode(6);
        // assert true
        System.out.println(Q101.instance.isSymmetric1(root2));
        System.out.println(Q101.instance.isSymmetric(root2));

    }
}
