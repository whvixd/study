package com.github.whvixd.springbootdemo.service.listener;

import com.github.whvixd.springbootdemo.service.event.SomethingRegisterEvent;
import com.github.whvixd.springbootdemo.service.model.Something;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by wangzhixiang on 2021/04/29.
 */
@Service
@Slf4j
public class SomethingAsyncEventListener implements ApplicationListener<SomethingRegisterEvent> {

    @Override
    @Async("commonExecutorPool")
    public void onApplicationEvent(@NonNull SomethingRegisterEvent somethingRegisterEvent) {
        Something something = (Something) somethingRegisterEvent.getSource();
        handlerSomething(something);
        log.info("SomethingAsyncEventListener->onApplicationEvent success");
    }

    private void handlerSomething(Something something) {
        log.info("SomethingAsyncEventListener->handler something:{}", something);
    }

}
