package com.github.whvixd.util;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 模仿ThreadLocal
 * Created by wangzhx on 2018/4/16.
 */
public class MyThreadLocal<T> {
    private Map<Thread, T> container = Maps.newConcurrentMap();
//    private Map<Thread,T> con = Collections.synchronizedMap(Maps.newHashMap());

    public void set(T value) {
        Thread currentThread = Thread.currentThread();
        if (Objects.nonNull(value)) {
            container.put(currentThread, value);
        }
    }

    public T get() {
        Thread currentThread = Thread.currentThread();
        T value = container.get(currentThread);
        if (!container.containsKey(currentThread) || Objects.isNull(value)) {
            container.put(currentThread, initialValue());
        }
        return value;
    }

    public void remove() {
        Thread currentThread = Thread.currentThread();
        container.remove(currentThread);
    }

    protected T initialValue() {
        return null;
    }

    protected Object initialValue(Supplier supplier) {
        return supplier.get();
    }
}
