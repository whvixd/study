package com.github.whvixd.demo.jdk.reflect;

import java.util.UUID;

/**
 * Created by wangzhixiang on 2021/04/27.
 */
public class IdGenTemp {
    public static String genId(String id){
        return id.substring(2)+ UUID.randomUUID().toString();
    }
}
