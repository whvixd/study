package com.github.whvixd.demo.springDemo;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by wangzhx on 2018/6/10.
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("course", new CourseBeanDefinitionParser());
    }
}
