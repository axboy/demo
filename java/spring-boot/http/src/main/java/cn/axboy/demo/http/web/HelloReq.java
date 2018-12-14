package cn.axboy.demo.http.web;

import lombok.Data;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/12/13 下午3:47
 * 测试post
 */
@Data
public class HelloReq {

    private String name;
    private int age;

    public HelloReq(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
