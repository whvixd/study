package com.github.whvixd.demo.jdk.util;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created by wangzhixiang on 2020/8/15.
 */
public class ZipFileDemo {

    public void readZipFile(int skippedLineStart, int skippedLineEnd) throws IOException {
        String resourceAsStream = getClass().getClassLoader().getResource("test/test_zip.zip").getPath();

        ZipFile zipFile = new ZipFile(resourceAsStream);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        AtomicInteger count = new AtomicInteger();


        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            System.out.println("read file name: " + zipEntry.getName());
            System.out.println("read file size: " + zipEntry.getSize());
            if (!zipEntry.isDirectory()) {
                InputStream inputStream = zipFile.getInputStream(zipEntry);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                while (reader.ready()) {
                    String line = reader.readLine();
                    count.incrementAndGet();
                    if (line == null) {
                        System.out.println("read over");
                        break;
                    }
                    // do something
                    if (count.get() < skippedLineStart || count.get() > skippedLineEnd) {
                        System.out.println("current read line:" + count.get());
                        System.out.println(line);
                    }

                }

            }
        }
    }


    public void download() throws IOException {
        // mock
        byte[] bytes = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("test/test_zip_copy.zip"));

        String baseURL = "/tmp/promotion/right-white";
        File file = new File(baseURL);
        String t = "";
        if (file.exists()) {
            file.createNewFile();
        }
        if (file.isDirectory()) {
            t = baseURL + "/test_zip_copy.zip";
            File file1 = new File(t);
            if (!file1.exists()) {
                file1.createNewFile();
            }
        }

        OutputStream outputStream = new FileOutputStream(new File(t));
        System.out.println(t);
        IOUtils.write(bytes, outputStream);
    }

    public static void writeBigTxt(int lineNumbers, String split) throws IOException {

        File file = new File("test_big_file.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        //使用true，即进行append file
        FileWriter fileWritter = new FileWriter(file.getName(), true);

        IntStream.range(0, lineNumbers).forEach(e -> {
            String s1 = String.valueOf(RandomUtils.nextInt());
            String s2 = String.valueOf(RandomUtils.nextInt());
            String s3 = String.valueOf(RandomUtils.nextInt());
            String line = s1 + split + s2 + split + s3 + "\n";
            try {
                fileWritter.write(line);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        fileWritter.close();

        System.out.println("finish");
    }

    public static void main(String[] args) throws IOException {
//        ZipFileDemo zipFileDemo = new ZipFileDemo();
//        zipFileDemo.readZipFile(1, 3);
//        zipFileDemo.download();
//        File file = new File("/Users/didi/logs/data/promotion/rights-white/");
//        System.out.println(file.canWrite());
//        System.out.println(file.canRead());
//        System.out.println(file.mkdirs());

        writeBigTxt(100, ",");

    }

    private static void compress() throws Exception {
        List<byte[]> srcFiles = Lists.newArrayList();
//        srcFiles.add(getBytes("404.png"));
//        srcFiles.add(getBytes("500.png"));
//        srcFiles.add(getBytes("back.jpg"));
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;
        ZipOutputStream zoStream = null;
        OutputStream os = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStream outputStream = new FileOutputStream(new File("E:\\test.zip"));
        try {
            zos = new ZipOutputStream(out);
            zoStream = new ZipOutputStream(outputStream);
            for (int i = 0; i < srcFiles.size(); i++) {
                byte[] buf = new byte[1024];
                zos.putNextEntry(new ZipEntry(i + ".png"));
                zoStream.putNextEntry(new ZipEntry(i + ".png"));
                int len;
                ByteArrayInputStream in = new ByteArrayInputStream(srcFiles.get(i));
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                    zoStream.write(buf, 0, len);
                }
                zos.closeEntry();
                zoStream.closeEntry();
                in.close();
            }
            zos.finish();
            byte[] bytes = out.toByteArray();

            os = new FileOutputStream(new File("E:\\test2.zip"));
            os.write(bytes);
            os.flush();
            os.close();
            out.close();
            zoStream.close();
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (zoStream != null) {
                try {
                    zoStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
