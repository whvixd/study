package com.github.whvixd.demo.thrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.logging.Logger;

/**
 * Created by wangzhx on 2020/5/19.
 */
public class GreetingClient {
    private static final Logger logger = Logger.getLogger(GreetingClient.class.getName());

    public static void main(String[] args) {
//        try {
//            TTransport transport = new TSocket("127.0.0.1", 9091);
//            transport.open();
//            TProtocol protocol = new TBinaryProtocol(transport);
//
//            GreetingService.Client client = new GreetingService.Client(protocol);
//
//            String name = "Eric";
//            logger.info("请求参数==>name为" + name);
//            String result = client.service("Eric");
//            logger.info("返回结果==>为" + result);
//            transport.close();
//        } catch (TTransportException e) {
//            e.printStackTrace();
//        } catch (TException e) {
//            e.printStackTrace();
//        }
    }
}
