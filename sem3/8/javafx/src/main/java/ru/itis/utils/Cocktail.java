package ru.itis.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cocktail {
    private String strDrink;
    private String strInstructions;
    private String strAlcoholic;
    private String strGlass;
    private String strIngredients;
    private String strDrinkThumb;
}
