package com.github.whvixd.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wangzhx on 2020/1/28.
 */
public class TestStringUtils {
    @Test
    public void test() {
        Assert.assertEquals(StringUtils.getResult("a:3,b:5,c:2@"), "a:3,b:5,c:2");
        Assert.assertEquals(StringUtils.getResult("a:3,b:5,c:2@a:1,b:2"), "a:2,b:3,c:2");
        Assert.assertEquals(StringUtils.getResult("a:0,b:5,c:2@b:2"), "b:3,c:2");

    }
}
