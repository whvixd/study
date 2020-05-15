package com.github.whvixd.demo.jdk.util;

import com.github.whvixd.util.UnsafeInstance;
import sun.misc.Unsafe;

/**
 * Created by wangzhx on 2020/5/15.
 */
public class UnSafeDemo {
    private Object o ;


    public UnSafeDemo(){
        o=new Object();
    }

    public Object getO() {
        return o;
    }

    public static void main(String[] args) {
        UnSafeDemo demo = new UnSafeDemo();
        demo.compareAndSetO(demo.getO(),new Object());
    }

    private static final Unsafe unsafe = UnsafeInstance.get();
    private static final long oOffset;
    static {
        try {
            oOffset = unsafe.objectFieldOffset(UnSafeDemo.class.getDeclaredField("o"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }

    private boolean compareAndSetO(Object expect,Object update){
        return unsafe.compareAndSwapObject(this,oOffset,expect,update);
    }

}
