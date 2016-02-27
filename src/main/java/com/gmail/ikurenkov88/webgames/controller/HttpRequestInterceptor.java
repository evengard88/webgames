package com.gmail.ikurenkov88.webgames.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.ikurenkov88.webgames.controller.request.Request;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

@WebFilter
public class HttpRequestInterceptor  implements Filter {
    @Autowired
    ObjectMapper objectMapper;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MyRequestWrapper myRequestWrapper = new MyRequestWrapper((HttpServletRequest) servletRequest);

        String body = myRequestWrapper.getBody();
        Request request = objectMapper.readValue(body, Request.class);
        String commandName = request.getMethod();
        myRequestWrapper.setCommand(commandName);
        filterChain.doFilter(myRequestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}