package com.github.whvixd.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.text.csv.*;
import lombok.Data;
import lombok.experimental.UtilityClass;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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


    public static List<String> importCsv(File file) {
        List<String> dataList = new ArrayList<String>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                dataList.add(line);
            }
        } catch (Exception e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return dataList;


    }

    public static void read(String filePath) {


        // 创建CSV读对象
        CsvReadConfig csvReadConfig = new CsvReadConfig();
        CsvReader csvReader = new CsvReader(FileUtil.file(filePath), csvReadConfig);

        CsvData read = csvReader.read();
        List<CsvRow> rows = read.getRows();
        rows.forEach(System.out::println);

    }

    public void readCsv() {
        List<A> read1 = CsvUtil.getReader().read(ResourceUtil.getUtf8Reader("/Users/didi/Downloads/xxx.csv"), A.class);
        System.out.println(read1.size());
        System.out.println(GsonUtil.toJson(read1));

    }


    @Data
    static class A {
        private String application_code;
        private Long old_amount;
        private Long new_amount;
    }
}
