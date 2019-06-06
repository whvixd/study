package com.github.whvixd.util.bean;

import net.sf.cglib.core.Converter;

public abstract class BeanCopier {
    abstract public void copy(Object from, Object to, Converter converter);
}
