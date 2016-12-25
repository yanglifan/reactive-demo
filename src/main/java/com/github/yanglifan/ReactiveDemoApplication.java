package com.github.yanglifan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.web.reactive.config.EnableWebReactive;
import redis.embedded.RedisServer;

import javax.annotation.PreDestroy;
import java.io.IOException;

@EnableWebReactive
@SpringBootApplication
public class ReactiveDemoApplication {

    @Autowired
    RedisConnectionFactory factory;

    public static void main(String[] args) {
        SpringApplication.run(ReactiveDemoApplication.class, args);
    }

    @Bean(destroyMethod = "stop")
    RedisServer embeddedRedisServer() throws IOException {
        RedisServer redisServer = new RedisServer(6379);
        redisServer.start();
        return redisServer;
    }

    @DependsOn("embeddedRedisServer")
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    /**
     * Clear database before shut down.
     */
    @PreDestroy
    public void flushTestDb() {
        factory.getConnection().flushDb();
    }
}
