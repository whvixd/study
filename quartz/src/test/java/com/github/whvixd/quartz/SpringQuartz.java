package com.github.whvixd.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangzhx on 2019/1/27.
 */
public class SpringQuartz {
    public static void main(String[] args) {
        ApplicationContext  context = new ClassPathXmlApplicationContext("classpath:spring/spring-quartz.xml");
    }
}
