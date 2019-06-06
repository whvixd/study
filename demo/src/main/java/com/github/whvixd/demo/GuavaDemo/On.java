package com.github.whvixd.demo.GuavaDemo;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class On {
    public static void main(String[] args) {
        String key = Joiner.on('\u0003').join("--", "1", "2", "3");
        System.out.println(key);
        List list = Splitter.on(" ").splitToList("12 121 21 21 21 ");
        System.out.println(list.size());
        list.forEach(x -> System.out.println(x));

        System.out.println(new TypeToken<Map<String, Map<String, List>>>() {
        }.getType());//获取类型

        List<String> strings = Lists.newArrayList("a", "b", "c");
        System.out.println("strings : " + Joiner.on(",").join(strings));
    }
}
