package com.github.whvixd.util;

import com.github.whvixd.util.function.FunctionHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wangzhixiang on 2021/12/17.
 */
public class FunctionHelperTest {
    @Test
    public void test(){
        boolean retryPredicate = FunctionHelper.retryPredicate(()->true);
        Assert.assertTrue(retryPredicate);
        retryPredicate = FunctionHelper.retryPredicate(()->false);
        Assert.assertTrue(!retryPredicate);
        retryPredicate = FunctionHelper.retryPredicate(()->mock());
        Assert.assertTrue(!retryPredicate);
    }

    private static boolean mock(){
        System.out.println("mock");
        throw new RuntimeException();
    }
}
