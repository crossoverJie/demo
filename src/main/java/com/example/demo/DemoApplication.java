package com.example.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.spi.InetAddressResolverProvider;
import java.util.Arrays;
import java.util.Locale;
import java.util.ServiceLoader;
import java.util.TimeZone;
import java.util.spi.TimeZoneNameProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		new Thread(() -> {
			InetAddress[] addresses = new InetAddress[0];
			try {
				System.out.println("====begin");
				addresses = InetAddress.getAllByName("fedora");
				InetAddress byAddress = InetAddress.getByAddress(new byte[]{127, 127, 10, 1});
				System.out.println("+++++" + byAddress.getHostName());

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			System.out.println("addresses = " + Arrays.toString(addresses));
		}).start();
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/get")
	public String get() {
		InetAddress[] fedoras = new InetAddress[0];
		try {
			fedoras = InetAddress.getAllByName("fedora");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("fedoras = " + Arrays.toString(fedoras));
		return "hello";
	}

}
