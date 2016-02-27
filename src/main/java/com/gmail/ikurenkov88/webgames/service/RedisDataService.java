package com.gmail.ikurenkov88.webgames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URL;

/**
 * Created by ilia on 27.02.16.
 */
@Service
public class RedisDataService
{
    // inject the actual template
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOperations;

    public String requestDataForSession(String id){
        Long increment = valueOperations.increment(id, 1);
        return String.valueOf(increment);
    }
}
