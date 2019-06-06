package com.github.whvixd.junit4;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by wangzhx on 2019/1/30.
 */
@RunWith(Parameterized.class)
@AllArgsConstructor
public class ParametersDemo {
    private int numberOne;
    private int numberTwo;
    private int expectedNumber;

    /**
     * 参考 https://www.cnblogs.com/byron0918/p/4801152.html
     * 1）该方法必须由@Parameters注解修饰
     * 2）该方法必须为public static的
     * 3）该方法必须返回Collection类型
     * 4）该方法的名字不做要求
     * 5）该方法没有参数
     */
    @Parameters
    public static Collection prepareData() {
        return Arrays.asList(new Integer[][]{
                {-1, -2, -1},
                {0, 2, 2},
                {-1, 1, 1},
                {1, 2, 2}});
    }

    @Test
    public void test() {
        int max = Integer.max(numberOne, numberTwo);
        Assert.assertEquals(expectedNumber, max);
    }

}
