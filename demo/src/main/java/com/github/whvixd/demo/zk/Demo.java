package com.github.whvixd.demo.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by wangzhx on 2020/4/20.
 */
public class Demo implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    private static Stat stat = new Stat();

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                countDownLatch.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {  //zk目录节点数据变化通知事件
                try {
                    System.out.println("配置已修改，新值为：" + new String(zooKeeper.getData(watchedEvent.getPath(), true, stat)));
                } catch (Exception e) {
                }
            }
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper("192.168.31.178:2181", 5000, new Demo());
        System.out.println(zooKeeper.getState());
        countDownLatch.await();
        byte[] username = zooKeeper.getData("/username", true, stat);
        byte[] person = zooKeeper.getData("/person", true, stat);
        List<String> children = zooKeeper.getChildren("/zookeeper", true);
        System.out.println(children);
        System.out.println(new String(username));
        System.out.println(new String(person));
        System.in.read();
    }
}
