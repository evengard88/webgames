package com.gmail.ikurenkov88.webgames.controller.response;

/**
 * Created by ilia on 27.02.16.
 */
public class Response {
    String method;
    String result;

    public Response() {
    }

    public Response(String method, String result) {
        this.method = method;
        this.result = result;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
