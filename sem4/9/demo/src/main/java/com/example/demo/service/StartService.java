package com.example.demo.service;

import com.example.demo.dto.NutritionDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Nutrition;
import com.example.demo.entity.Product;
import com.example.demo.util.NutritionMapper;
import com.example.demo.util.ProductMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class StartService {

    @Autowired
    private EntityManager entityManager;

    private static final String API_URL = "https://www.fruityvice.com/api/fruit/all";

    @PostConstruct
    public void saveProductsFromApi() {
        entityManager.getTransaction().begin();
        try {
            List<Product> products = fetchProductsFromAPI();
            for (Product product : products) {
                entityManager.persist(product);
                NutritionDto nutritionDto = product.getNutritions();
                if (nutritionDto != null) {
                    Nutrition nutrition = NutritionMapper.mapToNutrition(nutritionDto, product);
                    entityManager.persist(nutrition);
                }
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    private List<Product> fetchProductsFromAPI() {
        List<Product> products = new ArrayList<>();
        try {
            URL url = new URL(API_URL);
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder jsonString = new StringBuilder();
            while (scanner.hasNext()) {
                jsonString.append(scanner.nextLine());
            }
            scanner.close();

            ObjectMapper objectMapper = new ObjectMapper();
            ProductDto[] productDtoArray = objectMapper.readValue(jsonString.toString(), ProductDto[].class);
            for (ProductDto productDto : productDtoArray) {
                Product product = ProductMapper.mapToProduct(productDto);
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}
