package com.github.whvixd.springbootdemo.service.listener;

import com.github.whvixd.springbootdemo.service.event.SomethingRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by wangzhixiang on 2021/05/05.
 */
@Component
@Slf4j
public class SomethingEventOneListener {
    @EventListener(value = SomethingRegisterEvent.class, condition = "#event.source.type=='opt'")
    public void firstHandler(SomethingRegisterEvent event) {
        log.info("SomethingEventOneListener->firstHandler success,event:{}", event);
    }

    @EventListener(value = SomethingRegisterEvent.class, condition = "#event.source.type=='opt'")
    @Async("commonExecutorPool")
    public void secondHandler(SomethingRegisterEvent event) {
        log.info("SomethingEventOneListener->secondHandler success,event:{}", event);
    }
}
