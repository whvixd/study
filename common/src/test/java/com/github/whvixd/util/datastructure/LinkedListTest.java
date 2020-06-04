package com.github.whvixd.util.datastructure;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Created by wangzhx on 2020/4/22.
 */
public class LinkedListTest {

    private LinkedList<Integer> linkedList;

    @Before
    public void before(){
        linkedList = new LinkedList<>();
        IntStream.range(1,11).forEach(e-> linkedList.addFirst(e));
    }

    @Test
    public void testSize(){
        LinkedList<Integer> list = new LinkedList<>();
        IntStream.range(1,11).forEach(list::addFirst);
        assertEquals(10,list.size());
        list.remove(2);
        assertEquals(9,list.size());
        list.removeFirst();
        assertEquals(8,list.size());
        list.removeLast();
        list.removeLast();
        assertEquals(6,list.size());
        list.removeElement(5);
        assertEquals(5,list.size());

        list.addLast(12);
        assertEquals(6,list.size());
        list.addFirst(13);
        assertEquals(7,list.size());
        list.add(14);
        list.add(15);
        assertEquals(9,list.size());
    }

    @Test
    public void testHeadInsert(){
        assertEquals("10->9->8->7->6->5->4->3->2->1",linkedList.print());
        
    }

    @Test
    public void testTailInsert(){
        LinkedList<Integer>  linkedList = new LinkedList<>();
        IntStream.range(1,11).forEach(linkedList::addLast);
        assertEquals("1->2->3->4->5->6->7->8->9->10",linkedList.print());
        
    }

    @Test
    public void testRemoveFirst(){
        LinkedList<Integer>  linkedList = new LinkedList<>();
        IntStream.range(1,11).forEach(linkedList::addLast);
        Integer removeLast = linkedList.removeFirst();
        assertTrue(removeLast.equals(1));
        assertEquals("2->3->4->5->6->7->8->9->10",linkedList.print());
        
    }

    @Test
    public void testRemoveLast(){
        LinkedList<Integer>  linkedList = new LinkedList<>();
        IntStream.range(1,11).forEach(linkedList::addLast);
        Integer removeLast = linkedList.removeLast();
        assertTrue(removeLast.equals(10));
        assertEquals("1->2->3->4->5->6->7->8->9",linkedList.print());
        
    }

    @Test
    public void removeElement(){
        // 中间删除
        LinkedList<Integer>  linkedList = new LinkedList<>();
        IntStream.range(1,11).forEach(linkedList::addLast);
        assertTrue(linkedList.removeElement(3));
        assertEquals("1->2->4->5->6->7->8->9->10",linkedList.print());

        // 删除尾节点
        assertTrue(linkedList.removeElement(10));
        assertEquals("1->2->4->5->6->7->8->9",linkedList.print());

        // 删除头节点
        linkedList.removeElement(1);
        assertEquals("2->4->5->6->7->8->9",linkedList.print());
        assertFalse(linkedList.removeElement(100));
    }

    @Test
    public void testRemove(){
        LinkedList<Integer>  linkedList = new LinkedList<>();
        IntStream.range(1,11).forEach(linkedList::addLast);
        assertTrue(linkedList.remove(9).equals(10));
        assertTrue(linkedList.remove(0).equals(1));
    }

    @Test
    public void testSetAndGet(){
        LinkedList<Integer>  linkedList = new LinkedList<>();
        IntStream.range(1,11).forEach(linkedList::addLast);

        assertTrue(linkedList.set(0,101));
        assertTrue(linkedList.get(0).equals(101));
        assertTrue(linkedList.set(9,109));
        assertTrue(linkedList.get(9).equals(109));
    }

    @Test
    public void testContains(){
        assertTrue(linkedList.contains(1));
        assertFalse(linkedList.contains(101));
    }

    @Test
    public void testClear(){
        LinkedList<Integer>  linkedList = new LinkedList<>();
        IntStream.range(1,11).forEach(linkedList::addLast);

        linkedList.clear();
        assertEquals(0,linkedList.size());
    }

    @Test
    public void testClone(){
        LinkedList<Integer> clone = (LinkedList<Integer>) linkedList.clone();
        assertFalse(clone==linkedList);
        assertEquals(linkedList.print(),clone.print());
    }

    @After
    public void after(){
        linkedList.clear();
    }
}
