package com.github.whvixd.demo.springDemo;

import com.github.whvixd.demo.springDemo.model.AsyncDemo;
import com.github.whvixd.demo.springDemo.model.Model1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Container {
    public static void main(String[] args) {

//        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/bean.xml");
//        Entity.Student student = context.getBean(Entity.Student.class);
//        System.out.println(student);
//        context.registerShutdownHook();

//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/customBean.xml");
//        Entity.Course course = (Entity.Course) context.getBean("chinese");
//        System.out.println(course);
//        System.out.println(Long.toHexString(0xff).toUpperCase());

//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/factory-bean.xml");
//        Bean tom = context.getBean(Bean.class);
//        System.out.println(tom);


        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/temp-bean.xml");
//        context.getBean(Model1.class);
        AsyncDemo bean = context.getBean(AsyncDemo.class);
//        bean.asyncTest();
//        bean.aopDemo();
    }
}
