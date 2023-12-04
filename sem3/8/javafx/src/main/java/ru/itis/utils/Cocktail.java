package ru.itis.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cocktail {

    private List<Drink> drinks;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Drink {
        private String strDrink;

        private String strInstructions;

        private String strAlcoholic;
        private String strGlass;

        private String strIngredient1;

        private String strIngredient2;

        private String strIngredient3;

        private String strDrinkThumb;
    }
}
