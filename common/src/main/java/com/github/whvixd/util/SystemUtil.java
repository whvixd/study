package com.github.whvixd.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class SystemUtil {
    public void print(Object o){
        print(o,true);
    }

    public void print(Object o,boolean isOuted){
        if(isOuted){
            if(o.getClass().isArray()){
                if(o instanceof int[]){
                    o=Arrays.toString((int[]) o);
                }
                // todo 其他类型用到时待补充
            }
            System.out.println(o);
        }
    }
}
