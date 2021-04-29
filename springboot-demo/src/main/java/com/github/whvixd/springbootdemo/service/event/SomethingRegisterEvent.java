package com.github.whvixd.springbootdemo.service.event;

import com.github.whvixd.springbootdemo.service.model.Something;
import org.springframework.context.ApplicationEvent;

/**
 * Created by wangzhixiang on 2021/04/29.
 */
public class SomethingRegisterEvent extends ApplicationEvent{
    public SomethingRegisterEvent(Something something) {
        super(something);
    }
}
