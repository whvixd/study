package com.github.whvixd.util.datastructure;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * todo 替换为 assert
 * Created by wangzhx on 2020/4/22.
 */
public class LinkedListTest {

    private LinkedList<Integer> linkedList;

    @Before
    public void before(){
        linkedList = new LinkedList<>();
        IntStream.range(1,11).forEach(e-> linkedList.headInsert(e));
    }

    @Test
    public void testSize(){
        LinkedList linkedList = new LinkedList();
        System.out.println(linkedList.size());
    }

    @Test
    public void testHeadInsert(){
        LinkedList<Integer>  linkedList = new LinkedList<>();
        IntStream.range(1,11).forEach(linkedList::headInsert);
        linkedList.print();
    }

    @Test
    public void testTailInsert(){
        LinkedList<Integer>  linkedList = new LinkedList<>();
        IntStream.range(1,11).forEach(linkedList::tailInsert);
        linkedList.print();
    }

    @Test
    public void testHeadDelete(){
        linkedList.print();
        System.out.println("\n"+linkedList.headDeleteNode()+"\n");
        linkedList.print();
    }

    @Test
    public void testTailDelete(){
        linkedList.print();
        System.out.println();
        System.out.println(linkedList.tailDeleteNode());
        System.out.println();
        linkedList.print();
    }

    @Test
    public void deleteElement(){
        // 中间删除
        linkedList.print();
        System.out.println();
        linkedList.deleteElement(3);
        linkedList.print();

        // 删除尾节点
        System.out.println();
        linkedList.deleteElement(1);
        linkedList.print();

        // 删除头节点
        System.out.println();
        linkedList.deleteElement(10);
        linkedList.print();
    }
}
