package cn.axboy.demo.interview.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/8 20:33
 */
public class TestAtomic {
    public static void main(String[] args) {
        AtomicDemo demo = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(demo).start();
        }
    }
}

class AtomicDemo implements Runnable {

    private AtomicInteger atomicNum = new AtomicInteger(0);

    private int num = 0;

    // 不保证原子性
    private volatile int volatileNum = 0;


    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.format("%s: %d, %d, %d\n", Thread.currentThread(), getAtomicNum(), getNum(), getVolatileNum());
    }

    private int getAtomicNum() {
        return atomicNum.getAndIncrement();
    }

    private int getNum() {
        return num++;
    }

    private int getVolatileNum() {
        return volatileNum++;
    }
}
