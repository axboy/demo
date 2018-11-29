package cn.axboy.demo.redismessage.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

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
