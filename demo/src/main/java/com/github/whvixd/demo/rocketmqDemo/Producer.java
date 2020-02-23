package com.github.whvixd.demo.rocketmqDemo;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * Created by wangzhx on 2018/6/20.
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("defaultMQPushConsumerGroup");
        defaultMQProducer.setNamesrvAddr("192.168.0.102:9876");
        defaultMQProducer.start();
        for (int i = 0; i < 100; i++) {
            Message message = new Message("TopicA", "A", ("Hello Mq" + i).getBytes("UTF-8"));
            SendResult sendResult = defaultMQProducer.send(message);
            System.out.printf("%s%n", sendResult);
        }
        defaultMQProducer.shutdown();
    }
}
