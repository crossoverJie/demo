
```shell
mvn clean package -DskipTests
```

Run without javaagent is normal.
```shell
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

Output:
```shell
====begin
=====MyAddressResolver
addresses = [/127.127.10.1]
```

Run with javaagent is incorrect.
```shell
java -javaagent:opentelemetry-javaagent.jar \
      -Dotel.java.disabled.resource-providers=io.opentelemetry.instrumentation.resources.HostResourceProvider \
      -jar target/demo-0.0.1-SNAPSHOT.jar
```

Output:
```shell
[otel.javaagent 2024-04-02 13:23:15:429 +0800] [main] INFO io.opentelemetry.javaagent.tooling.VersionLogger - opentelemetry-javaagent - version: 1.32.0
====begin
Exception in thread "Thread-2" java.lang.RuntimeException: java.net.UnknownHostException: fedora: nodename nor servname provided, or not known
	at com.example.demo.DemoApplication.lambda$main$0(DemoApplication.java:47)
	at java.base/java.lang.Thread.run(Thread.java:1583)
Caused by: java.net.UnknownHostException: fedora: nodename nor servname provided, or not known
	at java.base/java.net.Inet6AddressImpl.lookupAllHostAddr(Native Method)
	at java.base/java.net.Inet6AddressImpl.lookupAllHostAddr(Inet6AddressImpl.java:52)
	at java.base/java.net.InetAddress$PlatformResolver.lookupByName(InetAddress.java:1211)
	at java.base/java.net.InetAddress.getAddressesFromNameService(InetAddress.java:1828)
	at java.base/java.net.InetAddress$NameServiceAddresses.get(InetAddress.java:1139)
	at java.base/java.net.InetAddress.getAllByName0(InetAddress.java:1818)
	at java.base/java.net.InetAddress.getAllByName(InetAddress.java:1688)
	at com.example.demo.DemoApplication.lambda$main$0(DemoApplication.java:44)
	... 1 more
```