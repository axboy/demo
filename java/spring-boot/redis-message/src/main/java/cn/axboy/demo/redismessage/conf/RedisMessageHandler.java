package cn.axboy.demo.redismessage.conf;

import lombok.Data;

@Data
public class RedisMessageHandler {

    public void handle(String message) {
        System.out.printf("messageHandler >>> %s\n", message);
    }
}
