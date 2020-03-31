package cn.axboy.demo.interview.thread;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/31 18:04
 */
public class ReportNumTask implements Runnable {

    private int num;

    public ReportNumTask(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("My num is " + num);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Caught exception");
            throw new RuntimeException(e);
        }
    }
}
