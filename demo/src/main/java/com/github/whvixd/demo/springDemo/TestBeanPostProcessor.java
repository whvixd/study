package com.github.whvixd.demo.springDemo;

import com.github.whvixd.demo.Entity;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.Objects;

public class TestBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Entity.Student student = null;
        if ("student".equals(beanName)) {
            student = (Entity.Student) bean;
            System.out.print(student.getName()+"-->");
            student.setName("李四");
            System.out.println(student.getName());
        }

        if (Objects.nonNull(student)) {
            return student;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
