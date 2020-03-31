package cn.axboy.demo.zookeeper.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/29 17:59
 */
public class ZKConnect {

    public static void main(String[] args) throws IOException, InterruptedException {
        final String zkServerPath = "127.0.0.1:2181,127.0.0.1:2182";
        final int sessionTimeout = 3000;

        System.out.println("test connect");
        ZooKeeper zk = new ZooKeeper(zkServerPath, sessionTimeout, new ZkWatcher());
        System.out.println("state: " + zk.getState());
        Thread.sleep(3000L);
        System.out.println("state: " + zk.getState());
    }
}
