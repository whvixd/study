package com.github.whvixd.demo.javaDemo;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;

import java.io.*;
import java.util.Map;
import java.util.Properties;

@Slf4j
/**
 * Properties可以创建xx.properties ; xx.xml文件
 */
public class PropertiesDemo {

    public static void readPropertyFile(String fileName, Map<String, String> map, String comments) {
        Long startTime = System.currentTimeMillis();

        Properties properties = new Properties();
        File file = new File("com.github.whvixd.demo.javaDemo" + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                log.info("{}文件创建成功", file.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (OutputStream outputStream = new FileOutputStream(file)) {

            map.forEach((k, v) -> {
                properties.setProperty(k, v);
            });

            if (fileName.endsWith(".xml")) {
                properties.storeToXML(outputStream, comments, String.valueOf(Charsets.UTF_8));
            } else {
                properties.store(outputStream, comments);
            }

            Long endTime = System.currentTimeMillis() - startTime;

            log.info("写入成功,耗时:{}", endTime);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Map map = Maps.newHashMap();
        map.put("name", "张三");
        map.put("age", "21");
        map.put("score", "99.9");
        readPropertyFile("zhangsan.properties", map, "个人信息");
        readPropertyFile("zhangsan.xml", map, "个人信息");

    }
}
