package com.github.whvixd.util;

/**
 * Created by wangzhx on 2018/4/23.
 */
public class DataClient {
    public static void main(String[] args) {
        ReadAndWriteData readAndWriteData= new ReadAndWriteData(3);
        new DataReader(readAndWriteData).start();
        new DataWrite("321",readAndWriteData).start();

        new DataReader(readAndWriteData).start();
        new DataWrite("abc",readAndWriteData).start();
    }
}
