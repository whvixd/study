package com.github.whvixd.demo.rocketmqDemo;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * Created by wangzhx on 2018/6/20.
 */
public class ProducerDemo {

    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("defaultMQPushConsumerGroup");
        defaultMQProducer.setNamesrvAddr("172.17.0.2:9876");
        defaultMQProducer.start();

        for (int i = 0; i < 100; i++) {
            try {
                Message message = new Message("TopicA","A",("Hello Mq" + i).getBytes("UTF-8"));
                SendResult sendResult = defaultMQProducer.send(message);

                System.out.printf("%s%n", sendResult);


            } catch (UnsupportedEncodingException | InterruptedException | MQBrokerException | RemotingException e) {
                e.printStackTrace();
            }
        }

        defaultMQProducer.shutdown();
    }
}
