package com.github.whvixd.junit4.rule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 * TestName： 记住在方法中使用的测试名称
 * Created by wangzhx on 2019/10/18.
 */
public class TestNameDemo {
    @Rule
    public TestName testName = new TestName();

    @Test
    public void testA() {
        Assert.assertEquals("testA", testName.getMethodName());
    }

    @Test
    public void testB() {
        Assert.assertEquals("testB", testName.getMethodName());
    }
}
