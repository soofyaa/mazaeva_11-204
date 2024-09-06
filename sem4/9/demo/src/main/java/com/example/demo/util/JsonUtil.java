package com.example.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
