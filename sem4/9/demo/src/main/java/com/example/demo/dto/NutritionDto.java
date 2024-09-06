package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NutritionDto {
    private double calories;
    private double fat;
    private double sugar;
    private double carbohydrates;
    private double protein;
}
