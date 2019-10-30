package com.github.whvixd.junit4.rule;

import org.junit.Assert;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;


/**
 * Created by wangzhx on 2019/10/21.
 */
@RunWith(Theories.class)
public class TheoriesDemo {

    @DataPoint
    public static String ARG_STRING = "string";
    @DataPoint
    public static Integer ARG_INTEGER = 999;

    @Theory
    public void testString(String string) {
        Assert.assertTrue(string.equals("string"));
    }

    @Theory
    public void testInteger(Integer integer) {
        Assert.assertTrue(integer.equals(999));
    }

}
