package cn.axboy.demo.interview.thread;

import cn.axboy.demo.interview.thread.ReportNumTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/3/31 18:07
 */
public class ExecutorTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 20; i++) {
            executorService.submit(new ReportNumTask(i));
        }
        System.out.println("20 tasks dispatched");
        //executorService.shutdownNow();
        try{

        }finally {

        }
    }
}
