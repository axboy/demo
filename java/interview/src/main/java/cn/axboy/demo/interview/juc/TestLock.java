package cn.axboy.demo.interview.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/8 21:22
 * 5个线程，把一个数从100自减到0
 */
public class TestLock {
    public static void main(String[] args) {
        LockDemo demo = new LockDemo();
        new Thread(demo).start();
        new Thread(demo).start();
        new Thread(demo).start();
        new Thread(demo).start();
        new Thread(demo).start();
    }
}

class LockDemo implements Runnable {

    private int num = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (num <= 0) {
                    break;
                }
                System.out.println(Thread.currentThread() + " " + num--);
            } finally {
                lock.unlock();
            }
        }
    }
}
