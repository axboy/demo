package cn.axboy.demo.interview.thread;

import lombok.SneakyThrows;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/5/19 13:02
 * 测试死锁，jps,jstack pid
 */
public class DeadLock extends Thread {

    protected Object tool;
    static Object fork1 = new Object();
    static Object fork2 = new Object();

    public DeadLock(Object obj) {
        this.tool = obj;
        if (obj == fork1) {
            this.setName("A");
        }
        if (obj == fork2) {
            this.setName("B");
        }
    }

    @SneakyThrows
    @Override
    public void run() {
        if (tool == fork1) {
            synchronized (fork1) {
                sleep(500);
                synchronized (fork2) {
                    System.out.println("A eat");
                }
            }
        }
        if (tool == fork2) {
            synchronized (fork2) {
                sleep(500);
                synchronized (fork1) {
                    System.out.println("B eat");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLock a = new DeadLock(fork1);
        DeadLock b = new DeadLock(fork2);
        a.start();
        b.start();
        sleep(1000);
    }
}
