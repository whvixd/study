package com.github.whvixd.util;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 模仿ThreadLocal
 * Created by wangzhx on 2018/4/16.
 */
public class ThreadLocal<T> {
    private Map<Thread, T> container = Maps.newConcurrentMap();

    public void set(T value) {
        Thread currentThread = Thread.currentThread();
        if (Objects.nonNull(value)) {
            container.put(currentThread, value);
        }
    }

    public T get() {
        Thread currentThread = Thread.currentThread();
        return container.get(currentThread);
    }

    public void remove() {
        Thread currentThread = Thread.currentThread();
        container.remove(currentThread);
    }

    protected Object initialValue(Supplier supplier) {
        return supplier.get();
    }
}
