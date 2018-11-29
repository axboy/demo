# 使用redis实现消息队列

消息队列主要踊跃异步化消息、应用解耦、限流等。具体的就不复制太多了。

用redis实现消息队列，主要就以下两个方案:

```
1. 使用redis的发布订阅机制
2. 使用redis list实现
```

### 使用redis的发布订阅机制

使用发布订阅，这种模式生产者(producer)和消费者(consumer)是1-M的关系，一条消息可以被多个消费者消费，当只有一个消费者时，可当做是1-1的消息队列。
首先，如果只有一个消费者，如果消费服务重启了，重启期间产生的消息是不能被消费的，可靠性得不到保证。
其次，没有办法通过增加消费者来加快消费进度，可以通过channel拆分的方式解决，扩展不灵活。
这种方案适用于一些不要求可靠性的消息传递。

- 定义一个消息处理类

```java
@Data
public class RedisMessageHandler {

    public void handle(String message) {
        System.out.printf("messageHandler >>> %s\n", message);
    }
}
```

- 相关配置

```java
@Configuration
public class RedisConfig {

    @Bean
    MessageListenerAdapter listenerAdapter() {
        //消息交给handler类处理，处理方法为handle()
        RedisMessageHandler handler = new RedisMessageHandler();
        return new MessageListenerAdapter(handler, "handle");
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅的主题为test
        container.addMessageListener(listenerAdapter, new PatternTopic("test-channel"));
        return container;
    }
}
```

- redisTemplate发布消息

```java
stringRedisTemplate.convertAndSend("test-channel", msg);
```

- redis-cli发布消息

```sh
> publish test-channel msg
```

### 使用redis list实现

利用redis的列表结构，生产者lpush，消费者rpop，设置超时时间，配置一个定时任务定时轮询。
也可以通过多个client来提高消费速度，对于专业的消息队列来说，该方案消息没有状态，过于简单。
消费失败依赖于系统处理。
以下是用spring-boot的实现，一个间隔10s的定时任务，循环获取消息消费，无消息等待30s。

```java
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
```

- redisTemplate发布消息

```java
ListOperations ops = stringRedisTemplate.opsForList();
ops.leftPush("test-list", msg);
```

- redis-cli发布消息

```sh
> lpush test-list 11
```

### 其它补充

- docker安装redis

```sh
docker run -d --name redis \
    -p 6379:6379 \
    -v `pwd`/data:/data \
    --privileged \
    --restart=always \
    redis redis-server --appendonly yes
```

### 参考

- [messaging-redis](https://spring.io/guides/gs/messaging-redis/)

- [菜鸟教程](http://www.runoob.com/redis/redis-tutorial.html)

### 最后

借用[小栋20](https://segmentfault.com/a/1190000012244418)的一句话

> Redis作为消息队列是有很大局限性的。因为其主要特性及用途决定它只能实现轻量级的消息队列。
> 没有绝对好的技术，只有对业务最友好的技术，谨此献给所有developer。