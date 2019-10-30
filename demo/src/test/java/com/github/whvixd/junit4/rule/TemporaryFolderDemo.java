package com.github.whvixd.junit4.rule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * TemporaryFolder：创建新文件，并在测试后删除
 * Created by wangzhx on 2019/10/18.
 */
public class TemporaryFolderDemo {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void temporaryFolder() throws IOException {
        File createdFolder = folder.newFolder("rule");

        File createdFile = folder.newFile("test.txt");



        assertTrue(createdFile.exists());
        assertTrue(createdFolder.exists());

    }
}
