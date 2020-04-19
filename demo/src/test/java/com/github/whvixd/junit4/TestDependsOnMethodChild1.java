package com.github.whvixd.junit4;

import org.testng.annotations.Test;

/**
 * Created by wangzhx on 2020/4/16.
 */
@Test(groups = "1")
public class TestDependsOnMethodChild1 extends TestDependsOnMethod {



    @Test()
    public void test3() {
        super.login();
        id = 3;
        System.out.println(id);
    }
}
