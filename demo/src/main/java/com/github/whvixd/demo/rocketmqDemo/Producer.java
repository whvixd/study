package com.github.whvixd.demo.rocketmqDemo;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.Message;

/**
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
