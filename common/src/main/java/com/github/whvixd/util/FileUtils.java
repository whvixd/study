package com.github.whvixd.util;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by wangzhixiang on 2021/1/18.
 */
@UtilityClass
public class FileUtils {

    public String deleteLine(String filePath, int indexLine) {
        int counter = 1;
        FileWriter writer = null;
        BufferedReader buffReader = null;
        StringBuilder tempTxt = new StringBuilder();
        try {
            File file = new File(filePath);
            FileReader freader = new FileReader(file);
            buffReader = new BufferedReader(freader);
            while (buffReader.ready()) {
                if (counter != indexLine) {
                    tempTxt.append(buffReader.readLine()).append("\n");
                } else {
                    buffReader.readLine();
                }
                counter++;
            }
            buffReader.close();
            writer = new FileWriter(file);
            writer.write(tempTxt.toString());
            writer.close();
        } catch (Exception e) {
            return "fail :" + e.getCause();
        }
        return "success!";
    }

}
