package cn.axboy.demo.rpc.grpc.impl;

import cn.axboy.demo.rpc.grpc.generate.HelloProto;
import cn.axboy.demo.rpc.grpc.generate.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2019/1/13 23:39
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void say(HelloProto.HelloReq request, StreamObserver<HelloProto.HelloResp> responseObserver) {
        System.out.printf(">>> name: %s, age: %d\n", request.getName(), request.getAge());
        HelloProto.HelloResp resp = null;

        try {
            if (request.getAge() < 0) {
                throw new Exception("Some exception.");
            }
            resp = HelloProto.HelloResp.newBuilder()
                    .setMsg(String.format("name: %s, age: %d", request.getName(), request.getAge()))
                    .build();
        } catch (Exception e) {
            responseObserver.onError(e);
        } finally {
            responseObserver.onNext(resp);
        }
        responseObserver.onCompleted();
    }
}
