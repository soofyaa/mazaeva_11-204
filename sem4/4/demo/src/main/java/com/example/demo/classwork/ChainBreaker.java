package com.example.demo.classwork;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ChainBreaker {

    private LocalDateTime currentTime = LocalDateTime.now();

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    @Scheduled(fixedRate = 1000)
    public void updateCurrentTime() {
        this.currentTime = LocalDateTime.now();
    }
}

