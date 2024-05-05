package com.example.demo;

import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.context.Context;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import lombok.SneakyThrows;
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

	@RequestMapping("/request")
	public String request(@RequestParam String name) {
//		Baggage.current().toBuilder().
//				put("my-transaction-id", "some-transaction-id").build()
//				.storeInContext(Context.current()).makeCurrent();

		HelloRequest request = HelloRequest.newBuilder()
				.setName(name)
				.build();
		log.info("request: {}", request);
		return myServiceStub.sayHello(request).getMessage();
//		return "";
	}
	@SneakyThrows
	@RequestMapping("/client")
	public String client() {
		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://127.0.0.1:8181/request?name=abc"))
				.build();

		new Thread(() -> {
			HttpResponse<String> response = null;
			try {
				response = client.send(request, HttpResponse.BodyHandlers.ofString());
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			log.info("response: {}", response.body());
		}).start();
		return "ok";
	}


}
