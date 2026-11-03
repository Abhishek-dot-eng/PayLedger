package com.payledger.infrastructure.redis;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Store a key-value pair
    public void set(String key, String value) {

        redisTemplate.opsForValue().set(key, value);

    }

    // Retrieve a value using its key
    public String get(String key) {

        return redisTemplate.opsForValue().get(key);

    }

    // Delete a key
    public Boolean delete(String key) {

        return redisTemplate.delete(key);

    }

    // Set expiration time
    public Boolean setExpiration(String key, long seconds) {

        return redisTemplate.expire(
                key,
                Duration.ofSeconds(seconds)
        );

    }
}