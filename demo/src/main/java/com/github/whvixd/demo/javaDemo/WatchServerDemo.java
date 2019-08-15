package com.github.whvixd.demo.javaDemo;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * 监听的文件目录（只能监听目录）
 * Created by wangzhx on 2019/8/9.
 */
public class WatchServerDemo {
    private void demo() throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
//        WatchKey poll = watchService.poll(3, TimeUnit.SECONDS);
        WatchKey watchKey = watchService.take();


        Path path = Paths.get("/Users/whvixd/Documents/IdeaProjects/workspace/study/demo/src/main/resources/tmp.log");
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_CREATE);
//        Stream<Path> list = Files.list(path);

        List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
        watchEvents.forEach(watchEvent -> {
            System.out.println(watchEvent.count());
        });
        watchKey.reset();


        // 增加jvm关闭的钩子来关闭监听
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                watchService.close();
            } catch (Exception e) {
            }
        }));
    }


    public static void main(String[] args) throws IOException {
        // 需要监听的文件目录（只能监听目录）
        String path = "/Users/whvixd/Documents/IdeaProjects/workspace/study/demo/src/main/resources";

        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path p = Paths.get(path);
        p.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_CREATE);

        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    WatchKey watchKey = watchService.take();
                    List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                    for (WatchEvent<?> event : watchEvents) {
                        System.out.println("[" + path + "/" + event.context() + "]文件发生了[" + event.kind() + "]事件");
                    }
                    watchKey.reset();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(false);
        thread.start();

        // 增加jvm关闭的钩子来关闭监听
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                watchService.close();
            } catch (Exception e) {
            }
        }));
    }
}
