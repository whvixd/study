package com.github.whvixd.demo.thrift.service;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Created by wangzhixiang on 2021/06/25.
 */
public class HelloThriftClient {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 9901;
    public static final int TIMEOUT = 30000;

    /**
     * @param userName
     */
    public void startClient(String userName) {
        TTransport transport = null;
        try {
            transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);

            HelloThriftService.Client client = new HelloThriftService.Client(protocol);
            transport.open();
            String result = client.sayHello(userName);
            System.out.println("Thrift client result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        HelloThriftClient client = new HelloThriftClient();
        client.startClient("thrift");

    }
}
