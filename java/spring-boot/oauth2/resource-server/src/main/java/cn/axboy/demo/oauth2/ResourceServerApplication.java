package cn.axboy.demo.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@SpringBootApplication
public class ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

    @GetMapping("/o2/getUser")
    public Map<String, String> getUser() {
        return Collections.singletonMap("name", "zcw");
    }

    @GetMapping("/getInfo")
    public Map<String, String> getInfo() {
        return Collections.singletonMap("info", "localhost");
    }

}
