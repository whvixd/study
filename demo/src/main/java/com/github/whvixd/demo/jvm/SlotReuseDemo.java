package com.github.whvixd.demo.jvm;

/**
 * Created by wangzhixiang on 2021/2/12.
 */
public class SlotReuseDemo {
    private static final int _64M = 64 * 1024 * 1024;

    /*
-verbose:gc

[GC (System.gc())  70233K->66776K(125952K), 0.0047733 secs]
[Full GC (System.gc())  66776K->66651K(125952K), 0.0068741 secs]
     */
    static void gc1() {
        byte[] slot = new byte[_64M];
        // 在作用域中，不会被回收：此变量之后，没有任何对此槽位操作，也没有复用，GC ROOTS仍保持关联
        // slot=null; //不过可手动将slot变量的空间置空，遇到占用大空间的变量时，可使用该手段
        System.gc();
    }

    static void gc2(){
        {
            byte[] slot = new byte[_64M];
        }
        // 也不会被回收
        System.gc();
    }

    static void gc3(){
        {
            byte[] slot = new byte[_64M];
        }
        // slot不在作用域中了，被i的槽位复用，先回收
        int i=1;
        System.gc();
    }

    public static void main(String[] args) {
        gc1();
    }
}
