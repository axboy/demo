package cn.axboy.demo.http.retrofit;

import cn.axboy.demo.http.web.HelloReq;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/12/13 下午5:00
 * test retrofit
 */
public interface TestRetrofitService {

    @GET("get")
    Call<Map> get(@Query("msg") String msg);

    @GET("getError")
    Call<String> getError();

    @GET("getHeader")
    Call<String> getHeader(@Header("msg") String msg);

    @POST("post")
    Call<Map> post(@Body HelloReq req);
}
