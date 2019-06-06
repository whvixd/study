package com.github.whvixd.junit4;

import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by wangzhx on 2019/1/30.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class BasicAnnotationDemo {

    @Rule
    public TestName testName = new TestName();

    @Before//在每个case执行之前都会run
    public void setUp() {
        System.out.println(String.format("before %s", testName.getMethodName()));
    }

    @After//在每个case执行完之后都会run
    public void tearDown() {
        System.out.println("after");
    }

    @Test
    public void testOne() {
    }

    @Test
    @Ignore//整个测试类run时忽略当前case
    public void testTwo() {
    }

    @Test(timeout = 1)//如果测试方法在制定的时间之内没有运行完，则测试也失败。
    public void testThree() throws InterruptedException {
        Thread.sleep(1);
    }

    @Test(expected = NullPointerException.class)
    public void testFour() {
        throw new NullPointerException();
    }
}
