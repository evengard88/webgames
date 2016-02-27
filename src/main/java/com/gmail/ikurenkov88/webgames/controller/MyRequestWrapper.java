package com.gmail.ikurenkov88.webgames.controller;

import org.eclipse.jetty.server.ByteBufferQueuedHttpInput;
import org.springframework.mock.web.DelegatingServletInputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.*;

/**
 * Created by ilia on 27.02.16.
 */
public class MyRequestWrapper extends HttpServletRequestWrapper {
    private final String body;
    private String command;

    public MyRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        body = stringBuilder.toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        DelegatingServletInputStream delegatingServletInputStream = new DelegatingServletInputStream(
                new ByteArrayInputStream(body.getBytes("UTF-8"))
        );
        return delegatingServletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }

    @Override
    public String getParameter(String name) {
        return super.getParameter(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return super.getParameterMap();
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return super.getParameterNames();
    }

    @Override
    public String[] getParameterValues(String name) {
        return super.getParameterValues(name);
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String getHeader(String name) {
        if (name.equals(command)) return "";
        return super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        if (name.equals(command)) return Collections.enumeration(Collections.singleton("test"));
        return super.getHeaders(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        //create a list
        List list = new ArrayList();

        //loop over request headers from wrapped request object
        HttpServletRequest request = (HttpServletRequest)getRequest();
        Enumeration e = request.getHeaderNames();
        while(e.hasMoreElements()) {
            //add the names of the request headers into the list
            String n = (String)e.nextElement();
            list.add(n);
        }

        //additionally, add the "username" to the list of request header names
        list.add(command);

        //create an enumeration from the list and return
        Enumeration en = Collections.enumeration(list);
        return en;
    }
}