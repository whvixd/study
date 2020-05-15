package com.github.whvixd.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by wangzhx on 2020/3/15.
 */
public class UnsafeInstance {
    public static Unsafe get() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
           throw new Error(e);
        }
    }

}
