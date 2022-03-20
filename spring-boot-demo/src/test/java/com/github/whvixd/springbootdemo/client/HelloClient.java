package com.github.whvixd.springbootdemo.client;

import com.github.whvixd.springbootdemo.SpringBootDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wangzhixiang on 2022/02/19.
 */
@SpringBootTest(classes = {SpringBootDemoApplication.class})
@RunWith(SpringRunner.class)
public class HelloClient {
    @Test
    public void testRestfulClient(){

    }
}
