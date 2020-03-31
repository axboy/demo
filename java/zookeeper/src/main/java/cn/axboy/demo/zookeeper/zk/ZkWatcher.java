package cn.axboy.demo.zookeeper.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/29 18:28
 */
public class ZkWatcher implements Watcher {

    public void process(WatchedEvent watchedEvent) {
        System.out.println("watcher event: " + watchedEvent);
    }
}
