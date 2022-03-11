package cn.axboy.demo.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class ApplicationStopListener implements ApplicationListener<ContextClosedEvent> {

    @Autowired(required = false)
    private ThreadPoolExecutor taskExecutor;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.warn("Application will exit");
        shutdownTaskExecutor();
        log.warn("Application exited, will not accept new requests.");
    }

    private void shutdownTaskExecutor() {
        if (taskExecutor == null) {
            log.debug("No task executor");
            return;
        }
        taskExecutor.shutdown();
        log.warn("Wait TaskExecutor shutdown");
        try {
            while (!taskExecutor.awaitTermination(2000, TimeUnit.MILLISECONDS)) {
            }
        } catch (InterruptedException e) {
            log.error("TaskExecutor shutdown interrupted");
            log.error(e.getMessage());
        }
        log.warn("TaskExecutor shutdown finish");
    }
}
