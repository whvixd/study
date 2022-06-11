package com.github.whvixd.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.text.csv.*;
import lombok.Data;
import lombok.experimental.UtilityClass;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
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
        List<A> read1 = CsvUtil.getReader().read(ResourceUtil.getUtf8Reader("/Users/xx/Downloads/xxx.csv"), A.class);
        System.out.println(read1.size());
        System.out.println(GsonUtil.toJson(read1));

    }


    public static String getFileContent(String fileName) {
        // mmap
        try (RandomAccessFile file = new RandomAccessFile(fileName, "r")) {
            FileChannel channel = file.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            int read = channel.read(byteBuffer);
            StringBuilder stringBuffer = new StringBuilder();
            while (read != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    char c = (char) byteBuffer.get();
                    stringBuffer.append(c);
                }
                byteBuffer.compact();
                read = channel.read(byteBuffer);
            }

            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Data
    static class A {
        private String application_code;
        private Long old_amount;
        private Long new_amount;
    }
}

