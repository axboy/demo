package cn.axboy.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
public class NacosBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosBootDemoApplication.class, args);
	}

}
