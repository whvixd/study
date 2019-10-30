package com.github.whvixd.junit4.rule;

import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.rules.Verifier;

import java.util.List;
import java.util.Objects;

/**
 * Verifier：可以在测试执行完成之后做一些校验，以验证测试结果是不是正确
 * Created by wangzhx on 2019/10/21.
 */
public class VerifierDemo {
    private List<String> list = Lists.newArrayList();

    @Rule
    public TestName testName = new TestName();

    @Rule
    public Verifier verifier = new Verifier() {
        /**
         * 每个case执行之后进行校验
         */
        @Override
        public void verify() {
            //不为空校验
            if (Objects.isNull(list) || list.size() == 0) {
                throw new RuntimeException("list size is null");
            } else {
                System.out.println(list);
            }
        }
    };

    @Test
    public void testOne() {
        System.out.println(testName.getMethodName());
    }

    @Test
    public void testTwo() {
        list.add("test");
    }

    /**
     * verifier 运行在 @After 步骤之后
     */
    @After
    public void afterExecute() {
        System.out.println(testName.getMethodName() + " after");
    }

}
