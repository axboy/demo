package cn.axboy.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/29 15:48
 */
@RestController
@RequestMapping("/config")
public class ConfigController {
    @Value("${customer.name:unknown}")
    private String name;

    @RequestMapping("/get")
    public String get() {
        return name;
    }
}
