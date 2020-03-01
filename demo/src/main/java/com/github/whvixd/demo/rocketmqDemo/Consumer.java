package com.github.whvixd.demo.rocketmqDemo;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;

/**
 * Created by wangzhx on 2018/6/20.
 */
public class Consumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("defaultMQPushConsumerGroup");
        defaultMQPushConsumer.setNamesrvAddr("192.168.0.103:9876");
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        defaultMQPushConsumer.subscribe("TopicA", "*");//subExpression订阅某个tag。* 为所有的tag
        defaultMQPushConsumer.registerMessageListener((MessageListenerOrderly) (messages, context) -> {
            messages.forEach(message -> System.out.println(new String(message.getBody())));
            return ConsumeOrderlyStatus.SUCCESS;
        });

        defaultMQPushConsumer.start();
    }
}
