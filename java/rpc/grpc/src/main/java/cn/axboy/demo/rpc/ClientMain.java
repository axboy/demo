package cn.axboy.demo.rpc;

import cn.axboy.demo.rpc.grpc.generate.HelloProto;
import cn.axboy.demo.rpc.grpc.generate.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2019/1/13 14:14
 */
public class ClientMain {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(HOST, PORT)
                .usePlaintext(true)
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub helloservice = HelloServiceGrpc.newBlockingStub(channel);
        HelloProto.HelloReq req = HelloProto.HelloReq
                .newBuilder()
                .setName("zcw")
                .setAge(-1)
                .build();
        HelloProto.HelloResp resp = helloservice.say(req);
        System.out.printf("<<< %s\n", resp.getMsg());
        channel.shutdown();
    }
}
