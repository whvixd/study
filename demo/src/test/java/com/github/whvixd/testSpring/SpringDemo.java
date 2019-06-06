package com.github.whvixd.testSpring;

import com.github.whvixd.demo.Entity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;

@Slf4j
public class SpringDemo {

    public static void main(String[] args) {
    }

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
}
