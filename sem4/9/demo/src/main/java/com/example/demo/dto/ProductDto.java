package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class ProductDto {
    private String name;
    private String family;
    private String order;
    private String genus;
    private NutritionDto nutritions;
}
