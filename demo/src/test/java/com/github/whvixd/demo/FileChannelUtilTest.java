package com.github.whvixd.demo;

import com.github.whvixd.util.FileChannelUtil;
import org.junit.Test;

/**
 * Created by wangzhx on 2019/12/24.
 */
public class FileChannelUtilTest {
    @Test
    public void test(){
        System.out.println(FileChannelUtil.getFileContent("/Users/whvixd/Documents/IdeaProjects/workspace/study/demo/src/test/resources/config/WatchServer.txt"));
    }
}
