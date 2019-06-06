package com.github.whvixd.util;

import java.lang.reflect.ParameterizedType;
import java.util.ServiceLoader;

/**
 * Created by wangzhx on 2019/5/24.
 */

public class Impl<T> {
    private ServiceLoader serviceLoader = ServiceLoader.load(getPatternClass());

    private Class getPatternClass() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<?> clazz = null;
        try {
            clazz = Class.forName(parameterizedType.getActualTypeArguments()[0].getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    T get() {
        return (T) serviceLoader.iterator().next();
    }

}
