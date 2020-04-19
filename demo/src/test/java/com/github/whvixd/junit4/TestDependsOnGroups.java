package com.github.whvixd.junit4;


import org.testng.annotations.Test;

/**
 * Created by wangzhx on 2020/4/15.
 */
public class TestDependsOnGroups {
    private int id;

    @Test(groups = "1")
    public void test1(){
        System.out.println(1);
        id=1;
    }

    @Test(dependsOnGroups = "1",groups = "2")
    public void test2(){
        System.out.println(2);
        System.out.println("id:"+id);

    }

    @Test(dependsOnGroups = "2",groups = "3")
    public void test3(){
        System.out.println(3);

    }

    @Test(dependsOnGroups = "3")
    public void test4(){
        System.out.println(4);

    }
    @Test(dependsOnGroups = "1")
    public void test5(){
        System.out.println(5);
        System.out.println("id:"+id);

    }
}
