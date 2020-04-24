package com.github.whvixd.demo.jdk.socket;

import com.github.whvixd.util.StreamUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Objects;

/**
 * Created by wangzhx on 2019/7/26.
 */
@Slf4j
public class SocketServerTask extends Thread {

    @Setter
    private Socket accept;

    @Override
    public void run() {
        try {
            if (Objects.isNull(accept)) {
                return;
            }
            InputStream inputStream = accept.getInputStream();
            StreamUtil.readStream2String(inputStream);
        } catch (IOException e) {
            log.error("", e);
        }
    }
}
