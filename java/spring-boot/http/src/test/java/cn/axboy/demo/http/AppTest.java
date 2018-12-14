package cn.axboy.demo.http;

import cn.axboy.demo.http.retrofit.TestRetrofitService;
import cn.axboy.demo.http.ribbon.TestRibbonService;
import cn.axboy.demo.http.web.HelloReq;
import feign.FeignException;
import okhttp3.*;
import okhttp3.MediaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import retrofit2.Retrofit;

import java.io.IOException;
/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/12/7 下午9:41
 * test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OkHttpClient okHttpClient;

    @Autowired
    private Retrofit retrofit;

    @Autowired
    private TestRibbonService testRibbonService;

    @Test
    public void testRestTemplate() {
        //test get method
        System.out.println(restTemplate.getForObject("/get?msg={msg}", String.class, "hello"));

        //test error request
        try {
            restTemplate.getForObject("/getError?msg={msg}", String.class, "hello");
        } catch (RestClientException e) {
            if (e instanceof HttpClientErrorException) {
                System.out.println(((HttpClientErrorException) e).getResponseBodyAsString());
            }
        }

        //test header
        HttpHeaders headers = new HttpHeaders();
        headers.add("msg", "header msg");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity responseEntity = restTemplate.exchange("/getHeader", HttpMethod.GET, httpEntity, String.class);
        System.out.println(responseEntity.getBody());

        //test post
        System.out.println(restTemplate.postForObject("/post", new HelloReq("zcw", 24), String.class));
    }

    @Test
    public void testOkHttp() throws IOException {
        String rootUrl = "http://localhost:8080";

        //test get
        Request req1 = new Request.Builder()
                .get()
                .url(String.format("%s/get?msg=okhttp", rootUrl))
                .build();
        Response resp1 = okHttpClient.newCall(req1).execute();
        System.out.println(resp1.body().string());

        // test 400 request
        Request req2 = new Request.Builder()
                .get()
                .url(String.format("%s/getError", rootUrl))
                .build();
        Response resp2 = okHttpClient.newCall(req2).execute();
        System.out.println(resp2.code());
        System.out.println(resp2.body().string());

        //test header
        Request req3 = new Request.Builder()
                .get()
                .url(String.format("%s/getHeader", rootUrl))
                .addHeader("msg", "header ok")
                .build();
        Response resp3 = okHttpClient.newCall(req3).execute();
        System.out.println(resp3.body().string());

        //test post
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{\"msg\":\"post ok\"}");
        Request req4 = new Request.Builder()
                .post(body)
                .url(String.format("%s/post", rootUrl))
                .build();
        Response resp4 = okHttpClient.newCall(req4).execute();
        System.out.println(resp4.body().string());
    }

    @Test
    public void testRetrofit() throws IOException {
        TestRetrofitService testRetrofitService = retrofit.create(TestRetrofitService.class);
        //test get
        System.out.println(testRetrofitService.get("get").execute().body());

        //get header
        System.out.println(testRetrofitService.getHeader("header").execute().body());

        //test error
        System.out.println(testRetrofitService.getError().execute().errorBody().string());

        //test post
        System.out.println(testRetrofitService.post(new HelloReq("zcw",24)).execute().body());
    }

    @Test
    public void testRibbon() {
        //test get
        System.out.println(testRibbonService.get("get"));

        //test header
        System.out.println(testRibbonService.getHeader("msg"));

        //test get error
        try {
            testRibbonService.getError();
        } catch (FeignException e) {
            e.printStackTrace();
        }

        //test post
        System.out.println(testRibbonService.post(new HelloReq("zcw", 24)));
    }
}
