package com.example.demo.util;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;

public class ProductMapper {

    public static Product mapToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setFamily(productDto.getFamily());
        product.setOrder(productDto.getOrder());
        product.setGenus(productDto.getGenus());
        product.setNutritions(productDto.getNutritions());
        return product;
    }
}
