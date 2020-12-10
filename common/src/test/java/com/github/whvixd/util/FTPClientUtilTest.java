package com.github.whvixd.util;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

/**
 * Created by wangzhixiang on 2020/12/10.
 */
public class FTPClientUtilTest {

    @Test
    public void testFTP(){
        FTPClientUtil test = new FTPClientUtil();
        FTPClient ftp = test.getFTPClient("127.0.0.1", 21, "didi","**");
        test.downLoadFTP(ftp, "/Users/didi/Downloads", "test.txt", "/Users/didi/Documents");
        //test.copyFile(ftp, "/file", "/txt/temp", "test.txt");
        //test.uploadFile(ftp, "test.txt", "/");
        //test.moveFile(ftp, "/file", "/txt/temp");
        //test.deleteByFolder(ftp, "/txt");
//        test.readFileByFolder(ftp, "/Users/didi/Downloads/marketing_partner_config");
        test.closeFTP(ftp);
        System.exit(0);
    }
}
