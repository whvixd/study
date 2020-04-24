package com.github.whvixd.demo.jdk.util;

import com.github.whvixd.util.GsonUtil;
import com.google.common.collect.Maps;
import javafx.util.Pair;
import lombok.Data;

import java.util.Map;

/**
 * Created by wangzhx on 2018/7/6.
 */
public class InnerClassDemo {
    @Data
    class A{
        private int a;
    }

    public static void main(String[] args) {
        Map map = Maps.newHashMap();
//        map.put("a",null);
        System.out.println(GsonUtil.fromJson(GsonUtil.toJsonWithNull(map),A.class).getA());

        Pair<String,Object> pair = new Pair<>("a","123");
        System.out.println(pair.getKey());
    }
}
