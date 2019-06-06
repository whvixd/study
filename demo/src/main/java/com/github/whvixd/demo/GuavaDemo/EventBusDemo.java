package com.github.whvixd.demo.GuavaDemo;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by wangzhx on 2018/6/7.
 *
 * EnventBus 是google的发布者-订阅者的工具包
 *
 * 支持多注册，多发布
 */
public class EventBusDemo {
    @Setter
    @Getter
    @AllArgsConstructor
    static class Message{
        private String msg ;
    }

    static class MessageListener{
        @Subscribe
        public void listener(Message message){
            System.out.println(message.getMsg()+"!");
        }

        @Subscribe
        public void listener(String event){
            System.out.println(event+".");
        }
    }

    public static void main(String[] args) {
        //AsyncEventBus
        EventBus eventBus = new EventBus("helloListener");
        eventBus.register(new MessageListener());

        eventBus.post(new Message("helloMessage"));
        eventBus.post("world");
    }
}
