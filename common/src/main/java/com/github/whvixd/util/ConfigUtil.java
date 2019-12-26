package com.github.whvixd.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 监听的文件目录（只能监听目录）
 * Created by wangzhx on 2019/8/9.
 */
@Slf4j
@UtilityClass
public class ConfigUtil {
    private static final String CONFIG = "config";
    private static final String EMPTY = "";
    private static final String SLASH = "/";

    public void execute(String fileName, Consumer<String> consumer) {
        fileName = fileName.startsWith(SLASH) ? fileName : SLASH + fileName;
        URL config = ConfigUtil.class.getClassLoader().getResource(CONFIG);
        String path = config == null ? EMPTY : config.getPath();
        consumer.accept(FileChannelUtil.getFileContent(path + fileName));
        WatchService watchService = null;
        try {
            watchService = FileSystems.getDefault().newWatchService();
            Path p = Paths.get(path);
            p.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException e) {
            log.error(EMPTY, e);
        }

        WatchService finalWatchService = watchService;
        String finalFileName = fileName;
        InvokeTask invokeTask = InvokeTask.newInstance(() ->
                executeTask(finalWatchService, consumer, path, finalFileName));
        invokeTask.setDaemon(false);
        invokeTask.start();
        shutDown(finalWatchService);
    }

    private void executeTask(WatchService finalWatchService, Consumer<String> consumer, String path, String fileName) {
        try {
            while (true) {
                WatchKey watchKey = finalWatchService.take();
                finalWatchService.poll(3, TimeUnit.SECONDS);
                List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                watchEvents.forEach(watchEvent -> {
                    consumer.accept(FileChannelUtil.getFileContent(path + fileName));
                    log.info("url:{},action:{}", path + fileName, watchEvent.kind());
                });
                watchKey.reset();
            }
        } catch (InterruptedException e) {
            log.error(EMPTY, e);
        }
    }

    private void shutDown(WatchService finalWatchService) {
        // 增加jvm关闭的钩子来关闭监听
        Runtime.getRuntime().addShutdownHook(InvokeTask.newInstance(() -> {
            try {
                finalWatchService.close();
            } catch (IOException e) {
                log.error("", e);
            }
        }));
    }

}
