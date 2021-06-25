package com.github.whvixd.demo.thrift.service;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by wangzhixiang on 2021/06/25.
 */
public class ThriftServerProxy {
    private static final int THRIFT_PORT=9901;
    public void startServer() throws TTransportException {
        //在这里调用了 HelloWorldImpl 规定了接受的方法和返回的参数
        TProcessor tprocessor = new HelloThriftService.Processor<>(new HelloThriftServiceImpl());

        TServerSocket serverTransport = new TServerSocket(THRIFT_PORT);
        TServer.Args tArgs = new TServer.Args(serverTransport);
        tArgs.processor(tprocessor);
        tArgs.protocolFactory(new TBinaryProtocol.Factory());

        TServer server = new TSimpleServer(tArgs);
        System.out.println("HelloThriftService start success!");
        server.serve();
    }

    public static void main(String[] args) {
        ThriftServerProxy proxy=new ThriftServerProxy();
        try {
            proxy.startServer();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

}
