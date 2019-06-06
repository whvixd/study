package com.github.whvixd.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzhx on 2018/3/22 18:33.
 */
@UtilityClass
public class XXToMap {
//todo
    /**
     * A.b.c.d:"Tom"
     * A.b.g:21
     *
     * A:{
     *     b:{
     *         c:{
     *             d:"Tom"
     *         },
     *         g:21
     *     }
     * }
     *
     */

    Map listtoMap(List<String> list) {
        list.forEach(L -> {
            String[] value = L.split("\\.");
            Map valueMap = Maps.newHashMap();

            List a = Splitter.on(".").splitToList(L);//A.b.c.d -> [A,b,c,d]
            a.forEach(o->{
                Map map1 = Maps.newHashMap();
                map1.put(o,value);
            });
        });
        return null;
    }
/*
    public static void main(String[] args) {
        String[] strings = "a.b.c:1".split("\\.");
        for (String s:strings){
            System.out.println(s);
        }

    }*/
}
