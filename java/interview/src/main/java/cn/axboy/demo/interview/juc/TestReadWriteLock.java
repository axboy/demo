package cn.axboy.demo.interview.juc;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/8 23:04
 */
public class TestReadWriteLock {

    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            if (i % 8 == 0) {
                new Thread(() -> {
                    demo.write(random.nextInt(100));
                }).start();
            } else {
                new Thread(() -> {
                    demo.read();
                }).start();
            }
        }
    }
}

class ReadWriteLockDemo {
    private int number;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        sleep();
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread() + " read: " + number);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write(int num) {
        sleep();
        lock.writeLock().lock();
        try {
            this.number = num;
            System.out.println(Thread.currentThread() + " write: " + number);
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
