package com.github.whvixd.util;

/**
 * Created by wangzhx on 2018/4/23.
 */
public class DataWrite extends Thread{
    private ReadAndWriteData readAndWriteData;
    private String data;

    public DataWrite(String data,ReadAndWriteData readAndWriteData){
        this.readAndWriteData = readAndWriteData;
        this.data = data;
    }

    @Override
    public void run() {
        while (true){
            readAndWriteData.write(data);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
