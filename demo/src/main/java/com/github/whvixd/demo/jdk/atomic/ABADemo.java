package com.github.whvixd.demo.jdk.atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
        String a = "a";
        String b = "b";
    public void atomicStampedReference(){
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>(a,1);
        String aReference = atomicStampedReference.getReference();
        int stamp = atomicStampedReference.getStamp();
        boolean compareAndSet = atomicStampedReference.compareAndSet(aReference, b, stamp, stamp + 1);
        System.out.println(compareAndSet);
        System.out.println("a reference:"+atomicStampedReference.getReference()+",stamp:"+atomicStampedReference.getStamp());
    }

    public void atomicMarkedReference(){
        AtomicMarkableReference<String> atomicMarkableReference = new AtomicMarkableReference<>(a,true);
        /**
         * 原始值，新值，原始标记，更新的标记
         */
        atomicMarkableReference.compareAndSet(atomicMarkableReference.getReference(),b,true,false);
        System.out.println();
    }

    public static void main(String[] args) {
        new ABADemo().atomicStampedReference();
    }
}
