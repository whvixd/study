package com.github.whvixd.demo.GuavaDemo;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
        EventBus eventBus = new EventBus("helloListener");
        eventBus.register(new MessageListener());


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
