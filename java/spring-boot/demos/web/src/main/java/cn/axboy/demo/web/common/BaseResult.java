package cn.axboy.demo.web.common;

import lombok.Data;

@Data
public class BaseResult<T> {

    private int code = 0;
    private String msg = "Success";
    private long timeMillis = System.currentTimeMillis();
    private T data;

    public BaseResult() {
    }

    public BaseResult(T data) {
        this.data = data;
    }

    public BaseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
