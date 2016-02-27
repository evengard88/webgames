package com.gmail.ikurenkov88.webgames;

import com.gmail.ikurenkov88.webgames.controller.HttpRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.Filter;
import java.util.Collections;

/**
 * Created by ilia on 27.02.16.
 */
@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableWebMvc
public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }
    @Bean
    public JedisConnectionFactory jedisConnFactory(){
        return new JedisConnectionFactory();
    }
    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnFactory){

        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnFactory);
        return redisTemplate;
    }
    @Bean
    HttpRequestInterceptor newInterceptor(){
        return new  HttpRequestInterceptor();
    }
}
