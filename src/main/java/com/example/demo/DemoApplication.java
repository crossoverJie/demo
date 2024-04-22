package com.example.demo;

import java.net.InetAddress;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GrpcClient("myService")
	private MyServiceGrpc.MyServiceBlockingStub myServiceStub;

	@RequestMapping("/get")
	public String get(@RequestParam String name) {
		HelloRequest request = HelloRequest.newBuilder()
				.setName(name)
				.build();
		log.info("request: {}", request);
		return myServiceStub.sayHello(request).getMessage();
	}

}
