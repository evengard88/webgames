package com.gmail.ikurenkov88.webgames.controller.request;

/**
 * Created by ilia on 27.02.16.
 */
public class Request {
    String method;
    Parameter parameter;

    public Request() {
    }

    public Request(String method, Parameter parameter) {
        this.method = method;
        this.parameter = parameter;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }
}
