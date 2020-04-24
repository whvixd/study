package com.github.whvixd.demo.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;

/**
 * 消息消费如何保证消息不被重复消费？
 * 1. 消费端消费后会响应broker，更新offset，但是响应时broker挂了，但成功消费了，可以利用redis，校验消息是否已消费
 *
 * Created by wangzhx on 2018/6/20.
 */
public class Consumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("defaultMQPushConsumerGroup");
        defaultMQPushConsumer.setNamesrvAddr("192.168.124.12:9876");
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        defaultMQPushConsumer.subscribe("TopicA", "*");//subExpression订阅某个tag。* 为所有的tag
        defaultMQPushConsumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {

            try {
                messages.forEach(message -> {
                            System.out.println(new String(message.getBody()));

                        }
                );
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                if (messages.get(0).getReconsumeTimes() == 3) {
                    //存数据库进行处理
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                } else {
                    //异常后重试机制
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }


        });

        defaultMQPushConsumer.start();
    }
}
