package com.github.whvixd.springbootdemo.service;

import com.github.whvixd.springbootdemo.SpringBootDemoApplication;
import com.github.whvixd.springbootdemo.service.record.RecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wangzhixiang on 2022/02/17.
 */
@SpringBootTest(classes = {SpringBootDemoApplication.class})
@RunWith(SpringRunner.class)
public class RecordServiceTest {

    @Autowired
    private RecordService recordService;

    @Test
    public void testAction(){
        recordService.action();
    }

}
