package cn.axboy.demo.interview.thread;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/2 10:40
 */
public class ReportNumThread extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("ReportNum run");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("ReportNum finally, always run?");
        }
    }
}
