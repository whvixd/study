package com.github.whvixd.dataStructure;

import com.github.whvixd.demo.dataStructure.BinaryTree;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Created by wangzhx on 2020/4/22.
 */
public class BinaryTreeTest {
    @Test
    public void insertTest(){
        BinaryTree<Integer> tree = new BinaryTree<>();
        IntStream.range(1,11).forEach(e->{
            tree.insert(e);
        });
        tree.preOrderFind(tree.getRoot());
    }
}
