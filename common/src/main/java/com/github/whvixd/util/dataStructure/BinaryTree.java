package com.github.whvixd.util.dataStructure;

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

        public void setValue(T value){
            this.value = value;
        }
        public void setLeft(Node<T> left){
            this.left = left;
        }
        public void setRight(Node<T> right) {
            this.right = right;
        }

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

    // 先序遍历
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

    private void print(Node<T> node){
        if(node!=null){
            System.out.println("node.value:"+String.valueOf(node.value));
        }
    }

}
