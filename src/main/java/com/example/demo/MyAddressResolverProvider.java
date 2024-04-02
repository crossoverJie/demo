package com.example.demo;

import java.net.spi.InetAddressResolver;
import java.net.spi.InetAddressResolverProvider;
public class MyAddressResolverProvider extends InetAddressResolverProvider {
    @Override
    public InetAddressResolver get(Configuration configuration) {
        return new MyAddressResolver();
    }
    @Override
    public String name() {
        return "MyAddressResolverProvider Internet Address Resolver Provider";
    }
}