package cn.axboy.demo.rpc;

import cn.axboy.demo.rpc.grpc.impl.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2019/1/13 14:14
 */
public class ServerMain {

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(PORT)
                .addService(new HelloServiceImpl())
                .build()
                .start();
        System.out.printf("Server started, Listening on %d\n", PORT);
        server.awaitTermination();
    }

}
