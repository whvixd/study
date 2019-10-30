package com.github.whvixd.junit4.category;

import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Created by wangzhx on 2019/10/21.
 */
public class Dog {
    @Test
    @Category(Animal.class)
    public void print() {
        System.out.println("Dog");
    }
}
