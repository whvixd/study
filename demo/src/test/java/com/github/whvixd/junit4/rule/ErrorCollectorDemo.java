package com.github.whvixd.junit4.rule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

/**
 * ErrorCollector： 在一个测试方法中收集多个错误,抛出异常时，程序不会停止运行，直到最后，会将所有搜集的异常抛出
 * Created by wangzhx on 2019/10/18.
 */
public class ErrorCollectorDemo {
    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Test
    public void errorCollector() {
        System.out.println("Hello");

        try {
           throw new RuntimeException();
        } catch (Throwable e) {
            errorCollector.addError(e);
        }

        Integer out = errorCollector.checkSucceeds(() -> {
            //异常 结果不能返回。无异常 返回结果
            Assert.assertTrue(true);
            return 1;
        });

        System.out.println(out);

        System.out.println("World");

    }

}
