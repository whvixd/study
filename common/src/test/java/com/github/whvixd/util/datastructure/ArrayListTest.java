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
        IntStream.range(0,10).forEach(list::add);
        Assert.assertTrue(10==list.size());
        IntStream.range(0,10).forEach(e-> Assert.assertTrue(e==list.get(e)));
    }
}
