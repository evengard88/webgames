package com.gmail.ikurenkov88.webgames.controller;

import com.gmail.ikurenkov88.webgames.controller.request.Request;
import com.gmail.ikurenkov88.webgames.controller.response.Response;
import com.gmail.ikurenkov88.webgames.service.RedisDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by ilia on 27.02.16.
 */
@RestController
public class RequestHandleController {

    @Autowired
    RedisDataService redisDataService;

    @RequestMapping(value = "/handler", method = RequestMethod.POST, headers = "ping")
    public String ping(HttpSession session,
                         @RequestBody Request request) {
        System.out.println("in controller " + request);
        System.out.println("in controller " + request.getParameter().getClientId());
        String e = redisDataService.requestDataForSession(session.getId());

        return "pong "+e;
    }

    @RequestMapping(value = "/handler", method = RequestMethod.POST, headers = "ping2")
    public String ping2(HttpSession session,
                        @RequestBody Request request) {
        return "Testing ping 2";
    }

}
