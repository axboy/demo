package cn.axboy.demo.mixdb.web;

import cn.axboy.demo.mixdb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/12/7 下午9:28
 */
@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private ProductService productService;

    @GetMapping("jpa")
    public Map jpa() {
        productService.jpaUpdate();
        return Collections.singletonMap("msg", "success");
    }

    @GetMapping("jooq")
    public Map jooq() {
        productService.jooqUpdate();
        return Collections.singletonMap("msg", "success");
    }

    @GetMapping("mybatis")
    public Map mybatis() {
        productService.mybatisUpdate();
        return Collections.singletonMap("msg", "success");
    }
}
