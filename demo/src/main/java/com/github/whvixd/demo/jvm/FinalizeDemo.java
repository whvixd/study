package com.github.whvixd.demo.jvm;

/**
 * Created by wangzhx on 2020/2/25.
 */
public class FinalizeDemo {
    private static FinalizeDemo finalizeDemo = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("invoke finalize");
        finalizeDemo = this;
    }

    public static void main(String[] args) throws InterruptedException {
        finalizeDemo = new FinalizeDemo();
        finalizeDemo = null;
        System.gc();//会调用finalize,异步执行

        Thread.sleep(500);
        if (finalizeDemo != null) {
            System.out.println("finalizeDemo!=null");
        } else {
            System.out.println("finalizeDemo==null");
        }

        finalizeDemo = null;
        System.gc();
        Thread.sleep(500);
        if (finalizeDemo != null) {
            System.out.println("finalizeDemo!=null");
        } else {
            System.out.println("finalizeDemo==null");
        }
    }
}
