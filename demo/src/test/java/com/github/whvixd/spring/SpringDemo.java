package com.github.whvixd.spring;

import com.github.whvixd.demo.Entity;
import com.github.whvixd.demo.spring.Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/temp-bean.xml"})
public class SpringDemo {
    @Autowired
    private Service service;

    @Test
    public void testSpringResource() throws IOException {
        Resource resource = new ClassPathResource("spring/bean.xml");

        BeanFactory beanFactory = new XmlBeanFactory(resource);
        Entity.Student s = beanFactory.getBean(Entity.Student.class);
        log.info("学生的名字:{}", s.getName());

        /*InputStream in = resource.getInputStream();
        int b = in.read();
        while (b != -1) {
            System.out.println((char) b);
        }
        in.close();*/
    }

    /**
     * https://segmentfault.com/a/1190000015190901
     */
    @Test
    public void testAsync() throws InterruptedException {
        System.out.println("start");
        service.test();
//        new Thread(()-> asyncDemo.asyncTest()).start();
        System.out.println("end");
        TimeUnit.SECONDS.sleep(20L);
    }
}
