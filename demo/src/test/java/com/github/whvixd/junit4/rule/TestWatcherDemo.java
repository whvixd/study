package com.github.whvixd.junit4.rule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static org.junit.Assert.fail;

/**
 * TestWatcher： 在方法执行期间添加事件的逻辑
 * Created by wangzhx on 2019/10/21.
 */
public class TestWatcherDemo {
    private static String watchedLog = "";

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            watchedLog += description + "\n";
            System.out.println(watchedLog);
        }

        @Override
        protected void succeeded(Description description) {
            watchedLog += description + "\n";
            System.out.println(watchedLog);
        }
    };

    @Test
    public void fails() {
        fail();
    }

    @Test
    public void succeeds() {
    }
}
