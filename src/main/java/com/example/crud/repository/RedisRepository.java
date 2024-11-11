package com.example.crud.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public Long sAdd(String key, String value) {
        return redisTemplate.opsForSet().add(key, value);
    }


}
