package com.example.crud.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class RedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public Long sAdd(String key, String value) {
        return redisTemplate.opsForSet().add(key, value);
    }


    public Set<String> hashSize(String key) {
        String keyPattern = key + ":*"; // 키 패턴 동적 생성
        return redisTemplate.keys(keyPattern);
    }
    public void hashPutAll(String key, Map<String, String> value) {
        redisTemplate.opsForHash().putAll(key, value);
    }

    // List에서 범위 조회
    public List<String> getListRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    // Hash 데이터 조회
    public Map<String, String> getHashEntries(String key) {
        return redisTemplate.opsForHash().entries(key).entrySet().stream()
                .collect(Collectors.toMap(
                        e -> String.valueOf(e.getKey()),
                        e -> String.valueOf(e.getValue())
                ));
    }

    public List<String> getAllKeys(String pattern) {
        return redisTemplate.keys(pattern).stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
