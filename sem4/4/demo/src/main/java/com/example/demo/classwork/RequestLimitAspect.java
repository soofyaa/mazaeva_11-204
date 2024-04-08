package com.example.demo.classwork;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class RequestLimitAspect {

    private LocalDateTime lastRequestTime;

    private int requestCount;

    @Before("execution(* com.example.demo.controllers.UserRegistrationController.registerUserAccount(..))")
    public void limitRequests() {
        LocalDateTime currentTime = LocalDateTime.now();

        if (lastRequestTime != null && currentTime.minusSeconds(30).isBefore(lastRequestTime)) {
            requestCount++;
            if (requestCount > 2) {
                throw new TooManyRequestsException("429 Too many requests");
            }
        } else {
            lastRequestTime = currentTime;
            requestCount = 1;
        }
    }
}

