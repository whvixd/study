package com.github.whvixd.demo.springDemo;

import com.github.whvixd.demo.Entity;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Created by wangzhx on 2018/6/10.
 */
public class CourseBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return Entity.Course.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String courseName = element.getAttribute("courseName");
        String score = element.getAttribute("score");

        if (StringUtils.hasLength(courseName)) {
            builder.addPropertyValue("courseName", courseName);
        }

        if (StringUtils.hasLength(score)) {
            builder.addPropertyValue("score", score);
        }
    }
}
