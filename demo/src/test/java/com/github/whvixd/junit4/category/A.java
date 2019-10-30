package com.github.whvixd.junit4.category;

import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Created by wangzhx on 2019/10/21.
 */
@Category(Character.class)
public class A {
    @Test
    public void print() {
        System.out.println("A");
    }
}
