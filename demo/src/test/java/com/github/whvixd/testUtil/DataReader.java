package com.github.whvixd.testUtil;

import com.github.whvixd.util.ReadAndWriteData;

/**
 * Created by wangzhx on 2018/4/23.
 */
public class DataReader extends Thread {

    public ReadAndWriteData readAndWriteData;



    public DataReader(ReadAndWriteData readAndWriteData) {
        this.readAndWriteData = readAndWriteData;
    }

    @Override
    public void run() {
        while (true) {
            String result = readAndWriteData.read();
            System.out.println(Thread.currentThread().getName() + "-->" + result);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
