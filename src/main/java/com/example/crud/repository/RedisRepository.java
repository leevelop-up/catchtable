package com.example.crud.repository;

import com.example.crud.dto.reservation.ReservationData;
import com.example.crud.util.RedisKeyUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class RedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    private final ZSetOperations<String, String> zSetOperations;
    private final ObjectMapper objectMapper;
    public Long sAdd(String key, String value) {
        return redisTemplate.opsForSet().add(key, value);
    }

    public Long sCard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    public Boolean sIsMember(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }
    public Long rPush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }


    public int getReservationCount(Integer shopId, String date, String time) {
        String reservationKey = RedisKeyUtil.getReservationKey(shopId, date, time);
        Integer count = Math.toIntExact(zSetOperations.zCard(reservationKey));
        return count == null ? 0 : count;
    }

    public void addReservation(Integer shopId, String date, String time, Integer memberNo) {
        String reservationKey = RedisKeyUtil.getReservationKey(shopId, date, time);
        long timestamp = Instant.now().getEpochSecond();
        ReservationData reservationData = new ReservationData(memberNo);
        try {
            String value = String.valueOf(memberNo);
            System.out.println(value);
            System.out.println(reservationKey);

            Boolean added = zSetOperations.add(reservationKey, value, timestamp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Set<String> hashSize(String key) {
        String keyPattern = key + ":*"; // 키 패턴 동적 생성
        return redisTemplate.keys(keyPattern);
    }
    public void hashPutAll(String key, Map<String, String> value) {
        redisTemplate.opsForHash().putAll(key, value);
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
