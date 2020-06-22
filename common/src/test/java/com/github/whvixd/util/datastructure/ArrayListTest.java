package com.github.whvixd.util.datastructure;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

/**
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

    private void init(ArrayList list){
        IntStream.range(0,10).forEach(list::add);
    }
}
