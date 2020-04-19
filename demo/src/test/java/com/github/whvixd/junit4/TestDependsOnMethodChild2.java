package com.github.whvixd.junit4;

import org.testng.annotations.Test;

/**
 * Created by wangzhx on 2020/4/16.
 */
@Test(groups = "2",dependsOnGroups = "1")
public class TestDependsOnMethodChild2 extends TestDependsOnMethod {

    @Test()
    public void test1(){

    }

    @Test()
    public void test3() {
        id = 3;
        System.out.println(id);
    }
}
