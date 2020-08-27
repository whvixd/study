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

//        String ls = ShellUtil.exec("ls ..", "\n");
//        System.out.println(ls);



        String wc = ShellUtil.exec("wc -l /Users/didi/Downloads/split_20200827_1598533431_aa", null);
        System.out.println(wc);

        String grep = ShellUtil.exec("grep '' /Users/didi/Downloads/split_20200827_1598533431_aa -c", null);
        System.out.println(grep);

        String sed = ShellUtil.exec("sed -n '$=' /Users/didi/Downloads/split_20200827_1598533431_aa", null);
        System.out.println(sed);
    }
}
