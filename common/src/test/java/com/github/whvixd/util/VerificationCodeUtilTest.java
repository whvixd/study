package com.github.whvixd.util;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by wangzhx on 2020/4/14.
 */
public class VerificationCodeUtilTest {
    @Test
    public void test() throws IOException {
        VerificationCodeUtil instance = VerificationCodeUtil.Instance();
        System.out.println(instance.getString());
        File file = new File("/Users/whvixd/Documents/IdeaProjects/workspace/study/common/src/test/resources/verificationCode/a.jpg");
        if(file.exists()){
            file.delete();
        }
        instance.write(file);
    }
}
