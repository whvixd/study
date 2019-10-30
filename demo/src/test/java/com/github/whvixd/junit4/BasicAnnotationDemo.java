package com.github.whvixd.junit4;

import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.MethodSorters;

/**
 * Created by wangzhx on 2019/10/11.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class BasicAnnotationDemo {

    @Rule
    public TestName testName = new TestName();

    /**
     * 所有test方法执行之前执行
     */
    @BeforeClass
    public static void setConfig() {
        MethodSorters aDefault = MethodSorters.DEFAULT;
        aDefault.getComparator();
        System.setProperty("profiles", "test");
    }
    /**
     * 在所有test方法执行完之后执行
     * 比如:连接关闭，资源回收等操作
     */
    @AfterClass
    public static void over() {
        System.out.println("all tests over");
    }

    /**
     * 在每个case执行之前都会run
     */
    @Before
    public void setUp() {
        System.out.println(String.format("config:%s", System.getProperty("profiles")));
    }

    /**
     * 在每个case执行完之后都会run
     */
    @After
    public void tearDown() {
        System.out.println("after");
    }

    @Test
    public void testOne() {
    }

    /**
     * 整个测试类run时忽略当前case
     */
    @Test
    @Ignore
    public void testTwo() {
    }

    /**
     * 如果测试方法在制定的时间之内没有运行完，则测试也失败。
     */
    @Test(timeout = 1)
    public void testThree() throws InterruptedException {
        Thread.sleep(1);
    }

    /**
     * 异常测试
     */
    @Test(expected = NullPointerException.class)
    public void testFour() {
        throw new NullPointerException();
    }

}
