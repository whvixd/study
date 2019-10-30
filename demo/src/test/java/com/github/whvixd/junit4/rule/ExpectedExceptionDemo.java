package com.github.whvixd.junit4.rule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * ExpectedException：抛出异常的灵活断言
 * <p>
 * Created by wangzhx on 2019/10/18.
 */
public class ExpectedExceptionDemo {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void throwsIllegalArgumentExceptionIfIconIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Negative value not allowed");

        throw new IllegalArgumentException("Negative value not allowed");
    }
}
