package com.github.whvixd.util.datastructure;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * 测试ArrayList
 *
 * Created by wangzhixiang on 2020/6/19.
 */
public class ArrayListTest {

    @Test
    public void testAddAndGet(){
        ArrayList<Integer> list = new ArrayList<>();
        init(list);
        Assert.assertTrue(10==list.size());
        IntStream.range(0,list.size()).forEach(e-> Assert.assertTrue(e==list.get(e)));
    }

    @Test
    public void testAdd(){
        ArrayList<Integer> list = new ArrayList<>();
        init(list);
        list.add(0,-1);
        IntStream.range(0,list.size()).forEach(e-> Assert.assertTrue(e-1==list.get(e)));
    }

    @Test
    public void testIndexOf(){
        ArrayList<Integer> list=new ArrayList<>();
        init(list);
        IntStream.range(0,list.size()).forEach(e-> Assert.assertTrue(e==list.indexOf(e)));
    }

    @Test
    public void testRemove(){
        ArrayList<Integer> list=new ArrayList<>();
        init(list);
        list.remove();
        IntStream.range(0,list.size()).forEach(e-> Assert.assertTrue(e==list.get(e)));

        list.remove(0);
        IntStream.range(0,list.size()).forEach(e-> Assert.assertTrue(e+1==list.get(e)));

        list.remove(new Integer(4));
        IntStream.range(3,list.size()).forEach(e-> Assert.assertTrue(e+2==list.get(e)));
    }

    @Test
    public void testSet(){
        ArrayList<Integer> list=new ArrayList<>();
        init(list);
        list.set(0,10);
        Assert.assertTrue(10==list.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClear(){
        ArrayList<Integer> list=new ArrayList<>();
        init(list);
        list.clear();
        list.get(0);
    }

    @Test
    public void testClone(){
        ArrayList<Integer> list=new ArrayList<>();
        init(list);
        ArrayList<Integer> clone=list.clone();
        Assert.assertTrue(10==clone.size());
        IntStream.range(0,clone.size()).forEach(e-> Assert.assertTrue(e==clone.get(e)));
    }

    @Test
    public void testToArray(){
        ArrayList<Integer> list=new ArrayList<>();
        init(list);
        Object[] objects = list.toArray();
        IntStream.range(0,objects.length).forEach(e-> Assert.assertTrue(e==(Integer) objects[e]));
    }

    @Test
    public void testContains(){
        ArrayList<Integer> list=new ArrayList<>();
        init(list);
        IntStream.range(0,list.size()).forEach(e-> Assert.assertTrue(list.contains(e)));
    }

    private void init(ArrayList<Integer> list){
        IntStream.range(0,10).forEach(list::add);
    }
}
