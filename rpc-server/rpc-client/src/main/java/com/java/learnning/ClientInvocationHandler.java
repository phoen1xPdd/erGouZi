package com.java.learnning;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class ClientInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    ClientInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        System.out.println("请求到这里");
        Object result = null;
        //包装请求数据
        ServerRequest serverRequest = new ServerRequest();
        serverRequest.setClassName(method.getDeclaringClass().getName());
        serverRequest.setMethod(method.getName());
        serverRequest.setArgs(args);

        //远程通信
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            socket = new Socket(host, port);

            //写出到server的流
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            //序列化
            outputStream.writeObject(serverRequest);
            outputStream.flush();

            //server写进来的流
            inputStream = new ObjectInputStream(socket.getInputStream());
            //反序列化
            result = inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != outputStream){
                    outputStream.close();
                }
                if(null!=inputStream){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
