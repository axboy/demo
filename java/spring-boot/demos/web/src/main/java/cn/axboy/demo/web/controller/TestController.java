package cn.axboy.demo.web.controller;

import cn.axboy.demo.web.annotation.EncryptBody;
import cn.axboy.demo.web.controller.test.TestDecodeReq;
import cn.axboy.demo.web.enums.AnimalEnum;
import cn.axboy.demo.web.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("hello")
    public void hello() {
        log.debug("hello");
    }

    @GetMapping("testException")
    public void testException(int type) {
        log.debug("testException: {}", type);
        if (type == 1) {
            throw new IllegalArgumentException("Test IllegalArgumentException");
        }
    }

    @EncryptBody
    @PostMapping("testStringBody")
    public void testStringBody(String str) {
        log.debug("testStringBody: {}", str);
    }

    @PostMapping(value = "testDecode", consumes = MediaType.TEXT_PLAIN_VALUE)
    public void testDecode(@EncryptBody TestDecodeReq req, String str) {
        log.debug("testDecode: {}", JsonUtil.toJson(req));
        log.debug("other param: {}", str);
    }

    @GetMapping("testEnumParam")
    public void testEnumParam(AnimalEnum animal) {
        log.debug("testEnumParam: {}", animal);
    }

    @GetMapping("sleep")
    public void sleep() throws InterruptedException {
        log.debug("before sleep: {}", Thread.currentThread().getId());
        Thread.sleep(20000);
        log.debug("after sleep: {}", Thread.currentThread().getId());
    }
}
