package com.java.learnning;

import java.io.Serializable;

/*
    约定的客户端和服务器之间传递的请求试题
 */
public class ServerRequest implements Serializable {
    private Object service;//服务名
    private String method;//方法名
    private Object[] args;//参数集合
    private String className;//类名


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object getService() {
        return service;
    }

    public void setService(Object service) {
        this.service = service;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
