package com.github.whvixd.springbootdemo.service;

import com.github.whvixd.springbootdemo.SpringBootDemoApplication;
import com.github.whvixd.springbootdemo.service.event.EventService;
import com.github.whvixd.springbootdemo.service.model.Something;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wangzhixiang on 2021/04/29.
 */
@SpringBootTest(classes = {SpringBootDemoApplication.class})
@RunWith(SpringRunner.class)
public class EventServiceTest {
    @Autowired
    private EventService eventService;

    @Test
    public void test() {
        Something something = new Something();
        something.setId("0");
        something.setName("test");
        something.setType("opt");
        eventService.publishSomething(something);

    }
}
