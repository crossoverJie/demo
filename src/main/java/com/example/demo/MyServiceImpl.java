package com.example.demo;

import io.grpc.stub.StreamObserver;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {

        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello ==> " + request.getName())
                .build();
        log.info("greeting: {}", reply.getMessage());
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}
