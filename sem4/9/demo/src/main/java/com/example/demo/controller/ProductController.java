package com.example.demo.controller;

import com.example.demo.service.ProductService;
import com.example.demo.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    private static final String ERROR = "Error occurred while processing the request.";
    @Autowired
    private ProductService productService;

    @Autowired
    private JsonUtil jsonUtil;

    @GetMapping("/cocktail")
    public ResponseEntity<String> getCocktailNutrients(
            @RequestParam List<String> ingredients,
            @RequestParam double weight
    ) {
        weight = weight/100;
        Map<String, Double> nutrientsMap = productService.calculateProductNutrients(ingredients, weight);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(jsonUtil.convertObjectToJson(nutrientsMap));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR);
        }
    }
}
