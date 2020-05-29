package com.github.whvixd.util.datastructure;

/**
 * Created by wangzhx on 2020/4/22.
 */
public class LinkedList<T> implements Queue<T>{

    // 头节点
    private Node<T> head;
    // 尾节点
    private Node<T> tail;

    public int size(){
        int size=0;
        Node<T> point=head;
        while (point!=null){
            point = point.next;
            if(point==tail){
                break;
            }
            size++;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    private static class Node<T>{
        T value;
        Node<T> next;
        Node<T> prev;
        public Node(T value, Node<T> next, Node<T> prev){
            this.value = value;
            this.next=next;
            this.prev=prev;
        }
        public Node(T value){
            this.value = value;
        }
        public Node(){}

        public T getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Node.value="+String.valueOf(value);
        }
    }

    public LinkedList(){
        if(this.head==null){
            head = new Node<>();
        }
        if(this.tail==null){
            tail = new Node<>();
        }
        head.next=tail;
        tail.prev=head;
    }

    // 头插
    public boolean headInsert(T o){
        Node<T> newNode = new Node<>(o);
        if(head.next!=null){
            newNode.next=head.next;
            newNode.prev=head;
            head.next.prev=newNode;
            head.next=newNode;
        }else {
            head.next=newNode;
            newNode.prev=head;
        }
        return true;
    }

    // 尾插
    public boolean  tailInsert(T o){
        Node<T> newNode = new Node<>(o);
        if(tail.prev!=null){
            newNode.next=tail;
            newNode.prev=tail.prev;
            tail.prev.next=newNode;
            tail.prev=newNode;
        }else {
            tail.prev=newNode;
            newNode.next=newNode;
        }
        return true;
    }

    // 头删
    public Node<T> headDelete(){
        if(size()>0){
            Node<T> deleteNode = head.next;
            Node<T> pointNode = deleteNode.next;
            head.next=pointNode;
            pointNode.prev=head;
            return deleteNode;
        }
        return null;
    }

    public T headDeleteNode(){
        return headDelete().getValue();
    }

    // 尾删
    public Node<T> tailDelete(){
        if(size()>0){
            Node<T> deleteNode = tail.prev;
            Node<T> pointNode = deleteNode.prev;
            pointNode.next=tail;
            tail.prev=pointNode;
            return deleteNode;
        }
        return null;
    }

    public T tailDeleteNode(){
        return tailDelete().getValue();
    }

    // TODO: 2020/4/22
    //根据 T 删除

    public void deleteElement(T o){
        Node<T> point=this.head;
        while (point.next!=null){
            T value=point.next.value;
            if(value!=null&&value==o){
                Node<T> next=point.next.next;
                if(next!=null){
                    point.next=next;
                    next.prev=point;
                }else {
                    point.next=null;
                }
            }else {
                point=point.next;
            }
        }
    }

    // 改
    // 查

    @Override
    public boolean add(T value){
        return headInsert(value);
    }

    @Override
    public T poll(){
        return tailDeleteNode();
    }

    public void print(Node<T> node){
        Node<T> pointNode = node==null?head.next:node;
        while (pointNode!=tail&&pointNode!=null){
            System.out.print(String.valueOf(pointNode.value));
            if(pointNode.next!=tail){
                System.out.print("->");
            }
            pointNode=pointNode.next;
        }
    }

    public void print(){
        print(null);
    }
}
