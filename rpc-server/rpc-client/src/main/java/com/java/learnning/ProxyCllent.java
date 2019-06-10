package com.java.learnning;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyCllent {
    public <T> T clientProxy(final Class<T> serviceCls,final String host,final int port) {
        //实现动态代理
        Class<?>[] classes = {serviceCls};
        InvocationHandler invocationHandler = new ClientInvocationHandler(host,port);
        return (T) Proxy.newProxyInstance(serviceCls.getClassLoader(),classes,invocationHandler);
    }
}
