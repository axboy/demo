package cn.axboy.demo.web.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestTask {

    @Scheduled(initialDelay = 5000, fixedDelay = 5000)
    public void helloTask() {
        log.debug("hello, {}, ID is {}.", Thread.currentThread().getName(), Thread.currentThread().getId());
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 5000)
    public void sleepTask() throws InterruptedException {
        log.debug("before sleep, {}, ID is {}.", Thread.currentThread().getName(), Thread.currentThread().getId());
        Thread.sleep(20000);
        log.debug("after sleep, {}, ID is {}.", Thread.currentThread().getName(), Thread.currentThread().getId());
    }

}
