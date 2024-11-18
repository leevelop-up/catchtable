package com.example.crud.controller;

import com.example.crud.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RedisController {

        private final RedisRepository redisRepository;
        @GetMapping("/redis")
        public String redis() {
            redisRepository.sAdd("1", "3");
            return "success";
        }
}
