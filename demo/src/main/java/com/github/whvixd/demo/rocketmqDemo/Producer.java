package com.github.whvixd.demo.rocketmqDemo;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.Message;

/**
 * 1. 如何保证mq消息不丢失？
 *  a. producer:i. 提供事务操作，若发送事务失败，回滚;ii. broker接受成功后，会响应ack，若失败了，则重试发送
 *  b. broker:自身维护 commitLog,支持主从异步、同步复制策略，broker挂了，消息也不会丢失
 *  c. consumer: 消费成功响应broker，更新offset，自身也会维护offset，若broker挂了，消费端也会重试
 *
 *
 * Created by wangzhx on 2018/6/20.
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("defaultMQPushConsumerGroup");

        boolean isTransaction = true;
        if (isTransaction) {
            //事务提供者
            TransactionMQProducer transactionMQProducer = new TransactionMQProducer("TransactionMQProducer");
            transactionMQProducer.setTransactionCheckListener(message -> {
                try {
                    //服务器端回调Producer。数据库操作
                } catch (Exception e) {
                    //若数据库操作失败了
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                System.out.println("服务器回查事务消息" + message);
                return LocalTransactionState.COMMIT_MESSAGE;
            });

            defaultMQProducer = transactionMQProducer;
        }
        defaultMQProducer.setNamesrvAddr("192.168.124.12:9876");

        defaultMQProducer.start();
        for (int i = 0; i < 100; i++) {
            String body = ("Hello Mq" + i);
            if (i == 88) {
                body = "88";
            }
            Message message = new Message("TopicA", "A", body.getBytes("UTF-8"));
            if (isTransaction) {
                defaultMQProducer.sendMessageInTransaction(message, (m, arg) -> {
                    //本地事务，由客户端回调
                    String bodyInTransaction = new String(message.getBody());
                    System.out.println("本地事务，由客户端回调 " + bodyInTransaction);
                    if ("88".equals(bodyInTransaction)) {
                        System.out.println("本地事务异常 " + bodyInTransaction);
                        return LocalTransactionState.ROLLBACK_MESSAGE;
                    }
                    return LocalTransactionState.COMMIT_MESSAGE;
                }, null);
            }
            SendResult sendResult = defaultMQProducer.send(message);
            System.out.printf("%s%n", sendResult);
        }
        defaultMQProducer.shutdown();
    }
}
