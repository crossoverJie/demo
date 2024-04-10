package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class DemoApplicationTests {

	@Test
	void contextLoads() throws IOException {
		URL url = new URL("jar:file:/Users/chenjie/Downloads/blog-img/demo/target/demo.jar!/");
		URLConnection uc = (new URL(url, "#runtime")).openConnection();
		JarFile jarFile = ((JarURLConnection)uc).getJarFile();
		System.out.println(jarFile.getName());
		final JarEntry entry = jarFile.getJarEntry("META-INF/services/java.net.spi.InetAddressResolverProvider");
		System.out.println(entry.getName());
	}
	@Test
	void contextLoads2() throws IOException {
		URL url = new URL("jar:file:/Users/chenjie/Documents/dev/github/spi-test/target/spi-test-1.0-SNAPSHOT.jar!/");
		URLConnection uc = (new URL(url, "#runtime")).openConnection();
		JarFile jarFile = ((JarURLConnection)uc).getJarFile();
		System.out.println(jarFile.getName());
		final JarEntry entry = jarFile.getJarEntry("META-INF/services/java.net.spi.InetAddressResolverProvider");
		System.out.println(entry.getName());
	}

	@Test
	void test() throws UnknownHostException {
		String hostName = InetAddress.getLocalHost().getHostName();
		System.out.println(hostName);
	}

}
