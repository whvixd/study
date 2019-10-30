package com.github.whvixd.junit4.rule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Created by wangzhx on 2019/10/21.
 */
public class TimeoutDemo {
    @Rule
    public Timeout timeout = Timeout.seconds(1);

    @Test(timeout = 3000)
    public void test() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test()
    public void test1() throws InterruptedException {
        Thread.sleep(2000);
    }

}
