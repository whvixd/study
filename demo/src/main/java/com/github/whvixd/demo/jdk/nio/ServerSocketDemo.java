package com.github.whvixd.demo.jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangzhixiang on 2021/06/04.
 */
public class ServerSocketDemo {
    private static final int MAX_THREAD = Runtime.getRuntime().availableProcessors();
    private ThreadPool pool = new ThreadPool(MAX_THREAD);

    private static final int PORT_NUMBER = 9999;

    public static void main(String[] args) throws Exception {
        new ServerSocketDemo().go();

    }

    public void go() throws Exception {
        int port = PORT_NUMBER;
        System.out.println("Listening on port:" + port);
        // 创建通道 ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定监听端口
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // 设置为非阻塞方式
        serverSocketChannel.configureBlocking(false);
        // 创建选择器
        Selector selector = Selector.open();

        // 通道注册到选择器
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // 一直阻塞，直到有数据请求
            int n = selector.select();
            if (n == 0) {
                continue;
            }
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel socket = server.accept();
                    socket.configureBlocking(false);
                    socket.register(selector, SelectionKey.OP_READ);
                    service(socket);
                }
                if (key.isReadable()) {
                    readDataFromSocket(key);
                }
                it.remove();
            }

        }

    }


    public void service(SocketChannel socket) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        buffer.put("hello client".getBytes());
        buffer.flip();
        socket.write(buffer);
    }

    public void readDataFromSocket(SelectionKey key) throws Exception {
        WorkThread thread = pool.getWork();
        if (thread == null) {
            return;
        }
        thread.serviceChannel(key);
    }

    private class ThreadPool {
        private final List<WorkThread> idle = new LinkedList<>();


        public ThreadPool(int poolSize) {
            for (int i = 0; i < poolSize; i++) {
                WorkThread thread = new WorkThread(this);
                thread.setName("worker" + (i + 1));
                thread.start();
                idle.add(thread);
            }

        }

        public WorkThread getWork() {
            WorkThread thread = null;
            synchronized (idle) {
                if (idle.size() > 0) {
                    thread = idle.remove(0);

                }
            }
            return thread;
        }

        public void returnWorker(WorkThread workThread) {
            synchronized (idle) {
                idle.add(workThread);
            }
        }

    }

    private class WorkThread extends Thread {
        private ByteBuffer buffer = ByteBuffer.allocate(1024);
        private ThreadPool pool;
        private SelectionKey key;

        public WorkThread(ThreadPool pool) {
            this.pool = pool;
        }

        public synchronized void run() {
            System.out.println(this.getName() + " is ready");
            while (true) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.interrupt();
                }
                if (key == null) {
                    continue;
                }
                System.out.println(this.getName() + " has been awaken");
                try {
                    drainChannel(key);
                } catch (Exception e) {
                    System.out.println("caught '" + e + "' closing channel");
                    try {
                        key.channel().close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                    key.selector().wakeup();
                }
                key = null;
                this.pool.returnWorker(this);

            }

        }

        synchronized void serviceChannel(SelectionKey key) {
            this.key = key;
            key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
            this.notify();
        }

        void drainChannel(SelectionKey key) throws Exception {
            SocketChannel channel = (SocketChannel) key.channel();
            buffer.clear();
            int count;
            while ((count = channel.read(buffer)) > 0) {
                buffer.flip();
                byte[] bytes;
                bytes = new byte[count];
                buffer.get(bytes);
                System.out.println(new String(bytes));
                buffer.clear();
            }
            if (count < 0) {
                channel.close();
                return;
            }
            key.interestOps(key.interestOps() | SelectionKey.OP_READ);
            key.selector().wakeup();
        }

    }
}
