package cn.axboy.demo.interview.juc;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/8 20:51
 * CountDownLatch: 闭锁
 */
public class TestLatch {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        LatchDemo demo = new LatchDemo(latch);
        for (int i = 0; i < 5; i++) {
            new Thread(demo).start();
        }
        System.out.println("main thread end");
    }
}

class LatchDemo implements Runnable {

    private CountDownLatch latch;
    private Random random = new Random();

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread() + " end");
        latch.countDown();
    }
}
