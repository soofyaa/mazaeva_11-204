package com.example.demo.util;

import com.example.demo.dto.NutritionDto;
import com.example.demo.entity.Nutrition;
import com.example.demo.entity.Product;

public class NutritionMapper {

    public static Nutrition mapToNutrition(NutritionDto nutritionDto, Product product) {
        Nutrition nutrition = new Nutrition();
        nutrition.setProduct(product);
        nutrition.setCalories(nutritionDto.getCalories());
        nutrition.setFat(nutritionDto.getFat());
        nutrition.setSugar(nutritionDto.getSugar());
        nutrition.setCarbohydrates(nutritionDto.getCarbohydrates());
        nutrition.setProtein(nutritionDto.getProtein());
        return nutrition;
    }
}
