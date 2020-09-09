package com.github.whvixd.demo.algorithm;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.RecursiveTask;

/**
 * Created by wangzhixiang on 2020/9/7.
 */
public class BigFileSortForkJoin {

    private static String BIG_FILE_NAME = "/Users/didi/Documents/test/test_big_file_sort/test_5000w.txt";
    // 创建的文件行数
    private static int LINE_COUNT = 100000;
    // 分割小文件的行数
    private static int BATCH_SIZE = 100000;
    private static String LINE_SEPARATOR = "\n";

    private static String SORT_FILE_NAME = "/Users/didi/Documents/test/test_big_file_sort/test_5000w_sort.txt";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        List<String> strings = BigFileSortForkJoin.separateFile();
        long separateFile = System.currentTimeMillis();
        System.out.println("separateFile:" + (separateFile - start));
        BigFileSortForkJoin.mergeFile(strings);
        System.out.println("mergeFile:" + (System.currentTimeMillis() - separateFile));
        // duration:1967752ms  33m
        System.out.println("duration:" + (System.currentTimeMillis() - start));
    }

    static class mergeTask extends RecursiveTask {

        @Override
        protected Object compute() {
            return null;
        }
    }


    private static List<String> separateFile() {
        List<String> fileNameList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BIG_FILE_NAME))) {
            int index = 0;
            int[] batchLineArray = new int[BATCH_SIZE];
            String line;
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                batchLineArray[i] = Integer.valueOf(line);
                if (i+1 == BATCH_SIZE) {
                    // 内容排序
                    Arrays.parallelSort(batchLineArray);
                    // 写小文件
                    String fileName = BIG_FILE_NAME + ".tmp." + index++;
                    try (FileWriter tmpWriter = new FileWriter(fileName)) {
                        for (Integer val : batchLineArray) {
                            tmpWriter.write(val + LINE_SEPARATOR);
                        }
                    }
                    fileNameList.add(fileName);
                    i=0;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNameList;
    }

    private static void mergeFile(List<String> fileNameList) {
        Map<BufferedReader, String> map = new HashMap<>();
        try (FileWriter writer = new FileWriter(SORT_FILE_NAME)) {
            for (String fileName : fileNameList) {
                BufferedReader tmpReader = new BufferedReader(new FileReader(fileName));
                map.put(tmpReader, tmpReader.readLine());
            }
            int count = 0;
            StringBuilder stringBuffer = new StringBuilder();
            for (; ; ) {
                boolean canRead = false;
                Map.Entry<BufferedReader, String> minEntry = null;
                for (Map.Entry<BufferedReader, String> entry : map.entrySet()) {
                    String value = entry.getValue();
                    if (value == null) {
                        continue;
                    }
                    // 获取当前 reader 内容最小 entry
                    if ((minEntry == null) || (Integer.valueOf(value) < Integer.valueOf(minEntry.getValue()))) {
                        minEntry = entry;
                    }
                    canRead = true;
                }
                // 当且仅当所有 reader 内容为空时，跳出循环
                if (!canRead) {
                    break;
                }
                count++;
                stringBuffer.append(minEntry.getValue()).append(LINE_SEPARATOR);
                if (count % 1000 == 0) {
                    writer.write(stringBuffer.toString());
                    stringBuffer.delete(0,stringBuffer.length());
                }
                minEntry.setValue(minEntry.getKey().readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 注意关闭分片文件输入流
            for (BufferedReader reader : map.keySet()) {
                IOUtils.closeQuietly(reader);
            }
        }
    }

}
