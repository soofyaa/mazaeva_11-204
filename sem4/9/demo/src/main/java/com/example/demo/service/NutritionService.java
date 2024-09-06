package com.example.demo.service;

import com.example.demo.entity.Nutrition;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NutritionService {

    @Autowired
    private EntityManager entityManager;

    public Nutrition getNutritionByProductName(String productName) {
        String queryString = "SELECT n FROM Nutrition n WHERE LOWER(n.product.name) = LOWER(:productName)";
        System.out.println("Query: " + queryString);
        Query query = entityManager.createQuery(queryString, Nutrition.class);
        query.setParameter("productName", productName.toLowerCase());
        System.out.println("Parameter: " + productName.toLowerCase());

        try {
            Nutrition nutrition = (Nutrition) query.getSingleResult();
            System.out.println("Found nutrition: " + nutrition);
            return nutrition;
        } catch (NoResultException e) {
            System.out.println("No nutrition found for product: " + productName);
            return null;
        }
    }

    public Map<String, Double> addToTotalNutrients(Map<String, Double> totalNutrients, Nutrition nutrition, double ingredientWeight) {
        totalNutrients.put("calories", totalNutrients.getOrDefault("calories", 0.0) + nutrition.getCalories() * ingredientWeight);
        totalNutrients.put("fat", totalNutrients.getOrDefault("fat", 0.0) + nutrition.getFat() * ingredientWeight);
        totalNutrients.put("sugar", totalNutrients.getOrDefault("sugar", 0.0) + nutrition.getSugar() * ingredientWeight);
        totalNutrients.put("carbohydrates", totalNutrients.getOrDefault("carbohydrates", 0.0) + nutrition.getCarbohydrates() * ingredientWeight);
        totalNutrients.put("protein", totalNutrients.getOrDefault("protein", 0.0) + nutrition.getProtein() * ingredientWeight);
        return totalNutrients;
    }
}
