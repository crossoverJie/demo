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
				// 获取默认的 TimeZoneNameProvider
				// 使用 ServiceLoader 加载所有可用的 TimeZoneNameProvider 实现
//				ServiceLoader<TimeZoneNameProvider> loader = ServiceLoader.load(TimeZoneNameProvider.class);
//
//				// 获取第一个实现
//				TimeZoneNameProvider provider = loader.findFirst().orElse(null);
//
//				// 获取美国洛杉矶时区的名称
//				String displayName = provider.getDisplayName("America/Los_Angeles", false, TimeZone.LONG, Locale.US);
//				System.out.println(displayName); // 输出：Pacific Standard Time
//
//				TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");
//				System.out.println("=====" + tz.getDisplayName());

//				ServiceLoader<InetAddressResolverProvider> load = ServiceLoader.load(InetAddressResolverProvider.class);
//				InetAddressResolverProvider inetAddressResolverProvider = load.findFirst().orElse(null);
//				System.out.println(inetAddressResolverProvider.name());

//				Thread.sleep(10* 1000);
				System.out.println("====begin");
				addresses = InetAddress.getAllByName("fedora");

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
