package com.github.whvixd.demo.dataStructure;

/**
 * Created by wangzhx on 2020/4/23.
 */
public interface Queue<T> extends Collection<T>{
    boolean add(T value);
    T poll();
}
