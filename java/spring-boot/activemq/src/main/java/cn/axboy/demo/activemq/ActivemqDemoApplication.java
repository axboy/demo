package cn.axboy.demo.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;

@SpringBootApplication
public class ActivemqDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqDemoApplication.class, args);
    }

    @JmsListener(destination = "test-queue")
    public void testQueue(String message) {
        System.out.println(message);
        if ("error".equals(message)) {
            throw new RuntimeException("Bad message");
        }
    }

}
