package com.github.whvixd.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by wangzhixiang on 2020/12/10.
 */
public class FTPClientUtilTest {

    @Test
    public void testFTP() throws IOException {
        FTPClientUtil test = new FTPClientUtil();
        FTPClient ftp = test.getFTPClient("127.0.0.1", 21, "xx","xx");
        FTPFile[] ftpFiles = ftp.listFiles("/Users/xx/Downloads");
        System.out.println(ftpFiles.length);
        for(FTPFile f:ftpFiles){
            System.out.println(f.getName());
        }
        test.downLoadFTP(ftp, "/Users/xx/Downloads", "test.txt", "/Users/xx/Documents");
        //test.copyFile(ftp, "/file", "/txt/temp", "test.txt");
        //test.uploadFile(ftp, "test.txt", "/");
        //test.moveFile(ftp, "/file", "/txt/temp");
        //test.deleteByFolder(ftp, "/txt");
//        test.readFileByFolder(ftp, "/Users/xx/Downloads/marketing_partner_config");
        test.closeFTP(ftp);
        System.exit(0);
    }
}
