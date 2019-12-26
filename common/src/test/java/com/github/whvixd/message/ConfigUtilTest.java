package com.github.whvixd.message;

import com.github.whvixd.util.ConfigUtil;
import com.github.whvixd.util.JacksonUtil;
import lombok.Data;

/**
 * Created by wangzhx on 2019/12/26.
 */
public class ConfigUtilTest {
    public static void main(String[] args) {
        ConfigUtil.execute("text.txt", configContent ->
                System.out.println(JacksonUtil.fromJson(configContent, ConfigUtilTestModel.class)));
    }

    @Data
    private static class ConfigUtilTestModel {
        private String name;
        private int age;
    }
}
