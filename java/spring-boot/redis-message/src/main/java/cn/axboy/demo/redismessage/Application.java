package cn.axboy.demo.redismessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@EnableScheduling
@SpringBootApplication
public class Application {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 用于向test-channel发送消息
     */
    @GetMapping("send")
    public Map sendMsg(String msg) {
        stringRedisTemplate.convertAndSend("test-channel", msg);
        return Collections.singletonMap("msg", "success");
    }

    /**
     * 用于向test-list增加消息
     */
    @GetMapping("push")
    public Map pushMsg(String msg) {
        ListOperations ops = stringRedisTemplate.opsForList();
        ops.leftPush("test-list", msg);
        return Collections.singletonMap("msg", "success");
    }

    /**
     * 定时任务，间隔10s
     */
    @Scheduled(fixedDelay = 10 * 1000)
    public void scheduleExecute() throws QueryTimeoutException {
        ListOperations<String, String> ops = stringRedisTemplate.opsForList();
        while (true) {
            String msg = ops.rightPop("test-list", 30, TimeUnit.SECONDS);
            if (msg == null) {
                System.out.println("empty list, break");
                break;
            }
            System.out.printf("schedule >>>>> %s\n", msg);
        }
    }
}
