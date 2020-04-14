package cn.axboy.demo.interview.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/8 20:36
 */
public class TestCallable {
    public static void main(String[] args) throws Exception {
        Callable<Integer> callable = new CallableDemo(1000000);
        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        new Thread(futureTask).start();
        System.out.println("-------main thread");
        System.out.println("-------thread num: " + Thread.activeCount());
        System.out.println("result: " + futureTask.get());
    }
}

class CallableDemo implements Callable<Integer> {

    private int num;

    public CallableDemo(int num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= num; i++) {
            sum += i;
        }
        return sum;
    }
}
