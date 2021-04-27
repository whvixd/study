package com.github.whvixd.util;

import com.github.whvixd.demo.jdk.reflect.IdGenUtil;
import org.junit.Test;

/**
 * Created by wangzhixiang on 2021/04/27.
 */
public class IdGenUtilTest {
    @Test
    public void test(){
        String s = IdGenUtil.genId();
        System.out.println(s);
    }
}
