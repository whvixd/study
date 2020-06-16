package com.github.whvixd.util.datastructure;

/**
 * 二叉树
 * Created by wangzhx on 2020/4/22.
 */
public class BinaryTree<T> {
    private Node<T> root;

    public Node<T> getRoot() {
        return root;
    }

    private static class Node<T> {
        private T value;      // 节点的值
        private Node<T> left; // 左子树
        private Node<T> right;// 右子树

        public Node(T value,Node<T> left,Node<T> right){
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    // 层次插入法
    public void insert(T o) {
        if(root==null){
            root = new Node<>(o,null,null);
            return;
        }
        Node<T> newNode = new Node<>(o, null, null);

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node t = queue.poll();
            if(t.left!=null){
                queue.add(t.left);
            }else {
                t.left = newNode;
                return;
            }
            if(t.right!=null){
                queue.add(t.right);
            }else {
                t.right = newNode;
                return;
            }
        }
    }

    public void insert(T[] array){
        if(array==null||array.length==0)return;
        for(T t:array) insert(t);
    }

    // 先序遍历
    public void preOrderFind(){
        preOrderFind(root);
    }

    public void preOrderFind(Node<T> node){
        if(node==null){
            return;
        }

        print(node);
        Node<T> left = node.left;
        Node<T> right = node.right;
        if(left!=null){
            preOrderFind(left);
        }
        if(right!=null){
            preOrderFind(right);
        }
    }

    // 中序遍历
    public void midOrderFind(Node<T> node){
        if(node==null){
            return;
        }
        Node<T> left = node.left;
        Node<T> right = node.right;
        if(left!=null){
            midOrderFind(left);
        }
        print(node);
        if(right!=null){
            midOrderFind(right);
        }
    }

    // 后序遍历
    public void postOrderFind(){
        postOrderFind(root);
    }

    public void postOrderFind(Node<T> node){
        if(node==null){
            return;
        }
        Node<T> left = node.left;
        Node<T> right = node.right;
        if(left!=null){
            postOrderFind(left);
        }
        if(right!=null){
            postOrderFind(right);
        }
        print(node);
    }

    public void levelOrderFind() {
        levelOrderFind(root);
    }

    public void levelOrderFind(Node<T> node) {
       Queue<Node<T>> queue=new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            Node<T> point = queue.poll();
            print(point);
            if(point.left!=null){
                queue.add(point.left);
            }
            if(point.right!=null){
                queue.add(point.right);
            }
        }
    }

    // 交换左子树与右子树
    public void exchangeLeftInRight(Node<T> node){
        if(node.left==null||node.right==null){
            return;
        }
        Node<T> tempNode = node.left;
        node.left = node.right;
        node.right=tempNode;

        exchangeLeftInRight(node.left);
        exchangeLeftInRight(node.right);
    }

    // 交换左子树与右子树
    public void exchangeLeftInRight(){
        exchangeLeftInRight(root);
    }

    public int height(){
        return height(root);
    }

    public boolean isSymmetric(){
        return isSymmetric(this.root);
    }

    private boolean isSymmetric(Node root) {
        if(root==null)return true;
        return compareLeftAndRight(root.left,root.right);
    }

    private boolean compareLeftAndRight(Node left,Node right) {
        if(left!=null&&right!=null){
            if(left.value==right.value){
                return compareLeftAndRight(left.left,right.right)&&compareLeftAndRight(left.right,right.left);
            }else {
                return false;
            }
        }else if(left==null&&right==null){
            return true;
        }else {
            return false;
        }
    }

    public int height2(Node root) {
        int front = -1, rear = -1;
        int last = 0, level = 0;
        Node[] queue = new Node[1000];
        if (root == null) {
            return 0;
        }
        queue[++rear]=root;
        while(front<rear){
            Node p=queue[++front];
            if(p.left!=null){
                queue[++rear]=p.left;
            }
            if(p.right!=null){
                queue[++rear]=p.right;
            }
            // 两者相等，则一层已遍历完成，front正好在下一层的最后一个节点的位子
            if(front==last){
                level++;
                last=rear;
            }
        }
        return level;
    }

    private int height(Node<T> node){
        if(node==null)return 0;
        int l=height(node.left);
        int r=height(node.right);
        return l>=r?l+1:r+1;
    }

    private void print(Node<T> node){
        if(node!=null){
            System.out.println("node.value:"+String.valueOf(node.value));
        }
    }

}
