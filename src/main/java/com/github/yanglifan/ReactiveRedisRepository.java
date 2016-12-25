package com.github.yanglifan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.nio.ByteBuffer;

@Repository
public class ReactiveRedisRepository {
    @Autowired
    RedisConnectionFactory connectionFactory;

    private ReactiveRedisConnection redisConnection;

    @PostConstruct
    public void init() throws Exception {
        redisConnection = connectionFactory.getReactiveConnection();
    }

    public Mono<Boolean> set(String key, String value) {
        return redisConnection.stringCommands().set(ByteBuffer.wrap(key.getBytes()), ByteBuffer.wrap(value.getBytes()));
    }
}
