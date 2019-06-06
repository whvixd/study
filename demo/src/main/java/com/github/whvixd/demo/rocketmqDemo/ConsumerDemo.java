package com.github.whvixd.demo.rocketmqDemo;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel.CLUSTERING;

/**
 * Created by wangzhx on 2018/6/20.
 */
public class ConsumerDemo {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("defaultMQPushConsumerGroup");
        defaultMQPushConsumer.setNamesrvAddr("172.17.0.2:9876");
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        defaultMQPushConsumer.subscribe("TopicA","*");//subExpression订阅某个tag。* 为所有的tag

        defaultMQPushConsumer.registerMessageListener((MessageListenerConcurrently)(messages,context)->{
            System.out.printf("%s Receive New Messages : %s %n",Thread.currentThread().getName(), messages);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        defaultMQPushConsumer.start();
    }
}
