package cn.axboy.demo.task.task;

import cn.axboy.demo.task.annotation.CloudTask;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TestTask {

    @CloudTask(key = "TestTask.helloTask", tag = "helloTask")
    @Scheduled(initialDelay = 5000, fixedDelay = 6666)
    public void helloTask() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }
        log.debug("hello, {}, ID is {}.", Thread.currentThread().getName(), Thread.currentThread().getId());
    }

    //@UniqueTask(key = "xxl", tag = "xxl-job")
    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        log.debug("XXL-JOB, Hello World.");
        XxlJobHelper.log("XXL-JOB, Hello World.");

        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
