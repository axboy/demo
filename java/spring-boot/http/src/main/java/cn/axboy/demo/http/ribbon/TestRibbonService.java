package cn.axboy.demo.http.ribbon;

import cn.axboy.demo.http.web.HelloReq;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/12/13 下午6:33
 */
@FeignClient(name = "feign-demo")
public interface TestRibbonService {

    @RequestMapping(value = "get", method = RequestMethod.GET)
    Map get(@RequestParam("msg") String msg);

    @RequestMapping(value = "getError", method = RequestMethod.GET)
    ResponseEntity getError();

    @RequestMapping(value = "getHeader", method = RequestMethod.GET)
    Map getHeader(@RequestHeader(value = "msg") String msg);

    @RequestMapping(value = "post", method = RequestMethod.POST)
    Map post(@RequestBody HelloReq data);
}
