package com.github.whvixd.util.datastructure;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Created by wangzhx on 2020/4/22.
 */
public class BinaryTreeTest {

    private BinaryTree<Integer> tree;

    @Before
    public void init() {
        tree = new BinaryTree<>();
        IntStream.range(1, 11).forEach(tree::insert);
    }

    @Test
    public void textPreOrderFind() {
        tree.preOrderFind(tree.getRoot());
    }

    @Test
    public void testExchangeLeftInRight() {
        tree.preOrderFind(tree.getRoot());
        tree.exchangeLeftInRight();
        System.out.println("------");
        tree.preOrderFind(tree.getRoot());
    }

    @Test
    public void testLevelOrderFind(){
        tree.levelOrderFind();
    }

    @Test
    public void testHeightAndIsSymmetric(){
        BinaryTree<Integer> tree=new BinaryTree<>();
        tree.insert(new Integer[]{1,2,2,3,4,4,3});
        assertEquals(3,tree.height());
        assertTrue(tree.isSymmetric());
        tree.insert(9);
        assertFalse(tree.isSymmetric());

    }
}
