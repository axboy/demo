package cn.axboy.demo.http.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/12/12 下午8:28
 * 几个rest接口，用于模拟服务
 */
@RestController
public class HelloController {

    @GetMapping("get")
    public Map get(String msg) {
        System.out.println("get >>>>>" + msg);
        return Collections.singletonMap("msg", msg);
    }

    @GetMapping("getError")
    public ResponseEntity getError() {
        System.out.println("getError >>>>>");
        return ResponseEntity.badRequest().body(Collections.singletonMap("msg", "error"));
    }

    @GetMapping("getHeader")
    public Map getHeader(@RequestHeader(value = "msg") String msg) {
        System.out.println("getHeader >>>>>" + msg);
        return Collections.singletonMap("msg", msg);
    }

    @PostMapping("post")
    public Map post(@RequestBody Map data) {
        System.out.println(data);
        return data;
    }
}
