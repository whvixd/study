package com.github.whvixd.demo.algorithm.leetcode.hard;

import com.github.whvixd.demo.algorithm.leetcode.TreeNode;
import com.github.whvixd.util.datastructure.LinkedList;
import com.github.whvixd.util.datastructure.Queue;

public enum Q297 {
    instance;

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    static class Codec {

        // 使用内存过大
        public String serialize1(TreeNode root) {
            if(root==null)return "";

            int front = -1, rear = -1;
            int last = 0, level = 1;
            int height=height(root);
            StringBuilder sb=new StringBuilder();

            // 满二叉树节点数:(2^h)-1,此处高度多一层，叶子节点的下层的空节点
            TreeNode[] queue = new TreeNode[(int)Math.pow(2,height+1)-1];
            queue[++rear]=root;
            while(front<rear&&level<=height){
                if(sb.length()>0){
                    sb.append(",");
                }
                TreeNode p=queue[++front];
                if(p==null){
                    sb.append("null");
                    continue;
                }
                sb.append(p.val);
                queue[++rear]=p.left;
                queue[++rear]=p.right;

                if(front==last){
                    level++;
                    last=rear;
                }
            }
            return sb.toString();
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root==null)return "";
            // 层级遍历
            Queue<TreeNode> queue=new LinkedList<>();
            int height=height(root);
            int rear=-1,front=-1;
            int last=0,level=1;

            queue.add(root);
            rear++;
            StringBuilder sb=new StringBuilder();
            while (!queue.isEmpty()&&level<=height){
                if(sb.length()>0){
                    sb.append(",");
                }
                TreeNode node = queue.poll();
                front++;
                if(node==null){
                    sb.append("null");
                    continue;
                }
                sb.append(node.val);

                TreeNode left = node.left;
                TreeNode right = node.right;
                if(queue.isEmpty()&&left==null&&right==null){
                    continue;
                }
                queue.add(left);
                rear++;
                queue.add(right);
                rear++;
                if(front==last){
                    level++;
                    last=rear;
                }
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data==null||data.length()==0)return null;
            String[] nodeString = data.split(",");
            TreeNode root = new TreeNode(Integer.valueOf(nodeString[0]));
            Queue<TreeNode> queue=new LinkedList<>();
            queue.add(root);
            for(int i=1;i<nodeString.length&&!queue.isEmpty();){
                TreeNode node = queue.poll();
                if(node==null)continue;
                if(node.left==null){
                    String s = nodeString[i++];
                    if("null".equals(s)){
                        node.left=null;
                    }else {
                        node.left=new TreeNode(Integer.valueOf(s));
                    }
                }
                queue.add(node.left);

                if(node.right==null){
                    String s = nodeString[i++];
                    if("null".equals(s)){
                        node.right=null;
                    }else {
                        node.right = new TreeNode(Integer.valueOf(s));
                    }
                }
                queue.add(node.right);
            }

            return root;
        }

        private int height(TreeNode node){
            if(node==null)return 0;
            int l=height(node.left);
            int r=height(node.right);
            return l>=r?l+1:r+1;
        }
    }



    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));

    public static void main(String[] args) {
//        TreeNode root=new TreeNode(1);
//        TreeNode leftOne=new TreeNode(2);
//        TreeNode rightOne=new TreeNode(3);
//
//        TreeNode leftTwo=new TreeNode(4);
//        TreeNode rightTwo=new TreeNode(5);
//
//        root.left=leftOne;
//        root.right=rightOne;
//
//        root.left.right=leftTwo;
//        root.right.left=rightTwo;
//
//
//        Codec codec = new Codec();
//        // assert [1,2,3,null,4,5,null]
//        String serialize = codec.serialize(root);
//        System.out.println(serialize);
//
//        TreeNode newRoot = codec.deserialize(serialize);


        TreeNode root=new TreeNode(1);
        TreeNode leftOne=new TreeNode(2);
        TreeNode rightOne=new TreeNode(3);

        TreeNode leftTwo=new TreeNode(4);
        TreeNode rightTwo=new TreeNode(5);

        root.left=leftOne;
        root.right=rightOne;

        root.right.left=leftTwo;
        root.right.right=rightTwo;


        Codec codec = new Codec();
        // assert [1,2,3,null,null,4,5]
        String serialize = codec.serialize(root);
        System.out.println(serialize);

        TreeNode newRoot = codec.deserialize(serialize);
        System.out.println(newRoot);
    }

}
