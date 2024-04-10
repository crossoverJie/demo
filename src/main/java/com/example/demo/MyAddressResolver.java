package com.example.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.spi.InetAddressResolver;
import java.util.stream.Stream;
public class MyAddressResolver implements InetAddressResolver {

    public MyAddressResolver() {
        System.out.println("=====MyAddressResolver");
    }

    @Override
    public Stream<InetAddress> lookupByName(String host, LookupPolicy lookupPolicy)
            throws UnknownHostException {
        if (host.equals("fedora")) {
            return Stream.of(InetAddress.getByAddress(new byte[] {127, 127, 10, 1}));
        }
        return Stream.of(InetAddress.getByAddress(new byte[] {127, 0, 0, 1}));
    }
    @Override
    public String lookupByAddress(byte[] addr) {
        System.out.println("++++++" + addr[0] + " " + addr[1] + " " + addr[2] + " " + addr[3]);
        return  "fedora";
    }
}