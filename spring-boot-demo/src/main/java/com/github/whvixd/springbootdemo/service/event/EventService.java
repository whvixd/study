package com.github.whvixd.springbootdemo.service.event;

import com.github.whvixd.springbootdemo.service.model.Something;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Created by wangzhixiang on 2021/04/29.
 */
@Service
@Slf4j
public class EventService {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publishSomething(Something something) {
        publisher.publishEvent(new SomethingRegisterEvent(something));
        log.info("EventService->publishSomething publish success,something:{}", something);
    }
}
