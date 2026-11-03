package com.payledger.infrastructure.redis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    void shouldStoreAndRetrieveValue() {

        String key = "test:" + UUID.randomUUID();

        redisService.set(key, "PayLedger");

        String result = redisService.get(key);

        assertEquals("PayLedger", result);

        redisService.delete(key);
    }

    @Test
    void shouldDeleteValue() {

        String key = "test:" + UUID.randomUUID();

        redisService.set(key, "temporary");

        Boolean deleted = redisService.delete(key);

        assertTrue(Boolean.TRUE.equals(deleted));

        assertNull(redisService.get(key));
    }
}