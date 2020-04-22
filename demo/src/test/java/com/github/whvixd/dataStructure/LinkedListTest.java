package com.github.whvixd.dataStructure;

import com.github.whvixd.demo.dataStructure.LinkedList;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Created by wangzhx on 2020/4/22.
 */
public class LinkedListTest {
    @Test
    public void testSize(){
        LinkedList linkedList = new LinkedList();
        System.out.println(linkedList.size());
    }

    @Test
    public void testHeadInsert(){
        LinkedList<Integer>  linkedList = new LinkedList<>();
        IntStream.range(1,11).forEach(e->{
            linkedList.headInsert(e);
        });
        linkedList.print();
    }

    @Test
    public void testTailInsert(){
        LinkedList<Integer>  linkedList = new LinkedList<>();
        IntStream.range(1,11).forEach(e->{
            linkedList.tailInsert(e);
        });
        linkedList.print();
    }
}
