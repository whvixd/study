package com.github.whvixd.junit4.category;

import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Created by wangzhx on 2019/10/21.
 */
public class Cat {
    @Test
    @Category(Character.class)
    public void print() {
        System.out.println("Cat");
    }
}
