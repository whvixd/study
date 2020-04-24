package com.github.whvixd.dataStructure;

import com.github.whvixd.util.dataStructure.BinaryTree;
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
}
