package com.example.demo.service;

import com.example.demo.entity.Nutrition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private NutritionService nutritionService;

    public Map<String, Double> calculateProductNutrients(List<String> ingredients, double weight) {
        Map<String, Double> totalNutrients = new HashMap<>();

        for (String ingredient : ingredients) {
            Nutrition nutrition = nutritionService.getNutritionByProductName(ingredient);
            if (nutrition != null) {
                totalNutrients = nutritionService.addToTotalNutrients(totalNutrients, nutrition, weight);
            }
        }

        return totalNutrients;
    }
}
