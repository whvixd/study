package com.github.whvixd.util.datastructure;

/**
 * head <-pn-> O <-pn-> O <-pn-> tail
 *
 * 双向链表，head、tail是空值指针，在初始化时赋值。
 *
 * Created by wangzhx on 2020/4/22.
 */
public class LinkedList<T> implements Queue<T>,Cloneable{

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
        init(this);
    }

    private void init(LinkedList list){
        list.head = new Node<>();
        list.tail = new Node<>();
        list.head.next=list.tail;
        list.tail.prev=list.head;
    }

    // 头插
    public boolean addFirst(T o){
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
    public boolean addLast(T o){
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
    public T removeFirst(){
        Node<T> node = removedNodeFirst();
        return node==null?null:node.getValue();
    }

    private Node<T> removedNodeFirst(){
        if(size()>0){
            Node<T> deleteNode = head.next;
            Node<T> pointNode = deleteNode.next;
            head.next=pointNode;
            pointNode.prev=head;
            return deleteNode;
        }
        return null;
    }

    // 尾删
    public T removeLast(){
        Node<T> node = removedNodeLast();
        return node==null?null:node.getValue();
    }

    private Node<T> removedNodeLast(){
        if(size()>0){
            Node<T> deleteNode = tail.prev;
            Node<T> pointNode = deleteNode.prev;
            pointNode.next=tail;
            tail.prev=pointNode;
            return deleteNode;
        }
        return null;
    }

    // 删除
    public boolean removeElement(Object o){
        Node<T> node = getElementNode(o);
        if(node==null){
            return false;
        }else {
            Node<T> prev=node.prev;
            Node<T> next=node.next;
            if(next==null){
                prev.next=null;
            }else {
                prev.next=next;
                next.prev=prev;
            }
            return true;
        }
    }

    // 删除
    public Object remove(int index){
        if(index<0||index>size()-1)throw new Error("index not greater size!");
        Node<T> point=this.head;
       for(int i=0;point.next!=null;i++){
           if(i==index){
               Node<T> removed=point.next;
               Node<T> next=point.next.next;
               if(next!=null){
                   point.next=next;
                   next.prev=point;
               }else {
                   point.next=null;
               }
               return removed.value;
           }else {
               point=point.next;
           }
       }
       return null;
    }

    // 改
    public boolean set(int index,T value){
        Node<T> node=getNode(index);
        if(node==null){
            return false;
        }else {
            node.value=value;
            return true;
        }
    }

    // 查
    public T get(int index){
        Node<T> node=getNode(index);
        return node==null?null:node.getValue();
    }

    private Node<T> getNode(int index){
        if(index<0||index>size())throw new Error("index not greater size!");
        Node<T> point=this.head;
        for(int i=0;point.next!=null;i++){
            if(i==index){
                return point.next;
            }else {
                point=point.next;
            }
        }
        return null;
    }

    private Node<T> getElementNode(Object o){
        Node<T> point=this.head.next;
        while (point!=null){
            if(o.equals(point.value)){
                return point;
            }else {
                point=point.next;
            }
        }
        return null;
    }

    // 包含
    public boolean contains(T value){
        Node<T> node = getElementNode(value);
        return node!= null;
    }

    public void clear(){
        for(Node node=this.head;node!=null;){
            Node point=node.next;
            node.prev=null;
            node.next=null;
            node.value=null;
            node=point;
        }
        head=null;
        tail=null;
    }

    // 深度克隆
    @SuppressWarnings("unchecked")
    public Object clone(){
        LinkedList<T> clone;
        try {
           clone= (LinkedList<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
        clone.init(clone);
        for(Node<T> point=this.head.next;point!=tail&&point!=null;point=point.next){
            clone.addLast(point.value);
        }
        return clone;
    }

    @Override
    public boolean add(T value){
        return addFirst(value);
    }

    @Override
    public T poll(){
        return removeLast();
    }

    public String print(Node<T> node){
        Node<T> pointNode = node==null?head.next:node;
        StringBuilder sb=new StringBuilder();
        while (pointNode!=tail&&pointNode!=null){
            sb.append(pointNode.value);
            System.out.print(String.valueOf(pointNode.value));
            if(pointNode.next!=tail){
                sb.append("->");
                System.out.print("->");
            }
            pointNode=pointNode.next;
        }
        System.out.println();
        return sb.toString();
    }

    public String print(){
        return print(null);
    }
}
