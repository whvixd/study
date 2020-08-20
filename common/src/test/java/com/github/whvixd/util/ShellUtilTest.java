package com.github.whvixd.util;

import org.junit.Test;

/**
 * Created by wangzhixiang on 2020/8/20.
 */
public class ShellUtilTest {
    @Test
    public void testExec(){
        String pwd = ShellUtil.exec("pwd");
        System.out.println(pwd);

        String ls = ShellUtil.exec("ls ..", "\n");
        System.out.println(ls);
    }
}
