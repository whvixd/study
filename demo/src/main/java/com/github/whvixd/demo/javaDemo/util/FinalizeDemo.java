package com.github.whvixd.demo.javaDemo.util;

/**
 * Created by wangzhx on 2020/2/25.
 */
public class FinalizeDemo {
    private static FinalizeDemo finalizeDemo = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        finalizeDemo = this;
    }

    public static void main(String[] args) {
        ClassLoader.getSystemClassLoader();
        finalizeDemo = new FinalizeDemo();
        finalizeDemo = null;
        System.gc();//会调用finalize

        if (finalizeDemo != null) {
            System.out.println("finalizeDemo!=null");
        } else {
            System.out.println("finalizeDemo==null");
        }

        finalizeDemo = null;
        System.gc();
        if (finalizeDemo != null) {
            System.out.println("finalizeDemo!=null");
        } else {
            System.out.println("finalizeDemo==null");
        }
    }
}
