package com.github.whvixd.demo.apache;

import com.google.common.collect.Maps;
import org.apache.commons.lang.text.StrSubstitutor;

import java.util.HashMap;

/**
 * Created by wangzhixiang on 2020/7/29.
 */
public class StringUtil {
    public static void main(String[] args) {
        String temple="{test}--{aaa}";
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("test","111");
        map.put("aa","222");

        StrSubstitutor strSubstitutor=new StrSubstitutor(map,"{","}",' ');
        System.out.println(strSubstitutor.replace(temple));
    }
}
