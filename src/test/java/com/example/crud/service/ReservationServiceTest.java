package com.example.crud.service;

import com.example.crud.domain.Reservation;
import com.example.crud.repository.ReservationJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDate;
import java.util.Collection;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    ReservationJpaRepository reservationJpaRepository;
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @BeforeEach
    void clear() {
        Collection<String> redisKeys = redisTemplate.keys("*");
        redisTemplate.delete(redisKeys);
    }
    @Test
    void registerReservation() {
        Reservation reservation = Reservation.builder()
                .memberNo(1)
                .shopId(1)
                .time("12:00")
                .status(1)
                .peopleCount(5)
                .date(LocalDate.now()).build();

        reservationJpaRepository.save(reservation);
    }
}