package com.github.whvixd.demo.guava;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangzhx on 2018/6/7.
 * <p>
 * EnventBus 是google的发布者-订阅者的工具包
 * <p>
 * 支持多注册，多发布
 */
public class EventBusDemo {
    @Setter
    @Getter
    @AllArgsConstructor
    static class Message {
        private String msg;
    }

    static class MessageListener {
        @Subscribe
        public void listener(Message message) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(message.getMsg() + "!");
        }

        @Subscribe
        public void listener(String event) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(event + ".");
        }

        @Subscribe
        public void listener(int event) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(event + "number");
        }

        //        @Subscribe
        public void listener(Long event) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(event + "Long");
        }
    }

    public static void main(String[] args) {
        //AsyncEventBus
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        EventBus eventBus = new AsyncEventBus(executor);

        //管理者，注册一个订阅者
        eventBus.register(new MessageListener());

        //管理者发布推出消息给订阅者
        eventBus.post(new Message("helloMessage"));
        eventBus.post(new Message("helloMessage"));

        new Thread(() -> {
            eventBus.post(12312321L);
            eventBus.post(Thread.currentThread().getName());
            eventBus.post(Thread.currentThread().getName());
        }).start();

        eventBus.post(new Message("helloMessage"));
        eventBus.post(new Message("helloMessage"));
        eventBus.post(new Message("helloMessage"));
        eventBus.post("world");


    }
}
