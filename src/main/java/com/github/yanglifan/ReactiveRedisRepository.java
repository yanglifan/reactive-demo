package com.github.yanglifan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

//@Repository
public class ReactiveRedisRepository {
    @Autowired
    RedisConnectionFactory connectionFactory;

    private ReactiveRedisConnection redisConnection;

    private RedisSerializer<String> serializer = new StringRedisSerializer();

    @PostConstruct
    public void init() throws Exception {
        redisConnection = connectionFactory.getReactiveConnection();
    }
}
