package com.example.demo;

import io.grpc.stub.StreamObserver;

import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        String value = Baggage.current().getEntryValue("request.name");
        log.info("request.name: {}", value);
        Executors.newFixedThreadPool(1).execute(() -> {
            myMethod(request.getName());
        });

        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello ==> " + request.getName())
                .build();
        log.info("greeting: {}", reply.getMessage());
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @SneakyThrows
    @WithSpan
    public void myMethod(@SpanAttribute("request.name") String name) {
        TimeUnit.SECONDS.sleep(1);
        log.info("myMethod:{}", name);
    }

}
