package com.github.whvixd.demo.rocketmqDemo;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;

/**
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
