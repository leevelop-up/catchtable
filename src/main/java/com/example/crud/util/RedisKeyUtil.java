package com.example.crud.util;

import org.springframework.stereotype.Component;

@Component
public class RedisKeyUtil {

    // 예약 개수 키 생성 (전역적으로 사용 가능)
    public static String getReservationKey(Integer shopId, String date, String time) {
        String changeTime = time.replace(":", "");
        return  "reservation:" + date + ":" + changeTime + ":" + shopId;
    }
}

