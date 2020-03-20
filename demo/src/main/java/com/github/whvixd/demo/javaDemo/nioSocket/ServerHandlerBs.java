package com.github.whvixd.demo.javaDemo.nioSocket;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * Created by wangzhx on 2020/3/8.
 */
public interface ServerHandlerBs {
    void handleAccept(SelectionKey selectionKey) throws IOException;

    String handleRead(SelectionKey selectionKey) throws IOException;
}
