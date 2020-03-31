package cn.axboy.demo.zookeeper.zk;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/29 18:28
 * 测试会话重连
 */
public class ZkSessionConnect {

    public static void main(String[] args) throws IOException, InterruptedException {
        final String zkServerPath = "127.0.0.1:2181,127.0.0.1:2182";
        final int sessionTimeout = 3000;

        ZooKeeper zk = new ZooKeeper(zkServerPath, sessionTimeout, new ZkWatcher());
        long sessionId = zk.getSessionId();
        byte[] sessionPasswd = zk.getSessionPasswd();

        System.out.println("test reconnect: " + Long.toHexString(sessionId));
        ZooKeeper zk1 = new ZooKeeper(zkServerPath, sessionTimeout, new ZkWatcher(), sessionId, sessionPasswd);
        System.out.println("state: " + zk.getState());
        Thread.sleep(3000L);
        System.out.println("state: " + zk.getState());
    }
}
