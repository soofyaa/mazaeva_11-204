package ru.itis.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomCocktailReceiver {
    public static Cocktail getRandomCocktail() throws IOException {
        URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/random.php");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            String strDrink = extractValue(response.toString(), "\"strDrink\":\"(.*?)\"");
            String strInstructions = extractValue(response.toString(), "\"strInstructions\":\"(.*?)\"");
            String strAlcoholic = extractValue(response.toString(), "\"strAlcoholic\":\"(.*?)\"");
            String strGlass = extractValue(response.toString(), "\"strGlass\":\"(.*?)\"");
            String strDrinkThumb = extractValue(response.toString(), "\"strDrinkThumb\":\"(.*?)\"");

            StringBuilder strIngredientsBuilder = new StringBuilder();
            for (int i = 1; i <= 3; i++) {
                String ingredient = extractValue(response.toString(), "\"strIngredient" + i + "\":\"(.*?)\"");

                if (!ingredient.trim().isEmpty()) {
                    if (strIngredientsBuilder.length() > 0) {
                        strIngredientsBuilder.append(", ");
                    }
                    strIngredientsBuilder.append(ingredient);
                }
            }
            String strIngredients = strIngredientsBuilder.toString();

            return new Cocktail(strDrink, strInstructions, strAlcoholic, strGlass, strIngredients, strDrinkThumb);
        } finally {
            connection.disconnect();
        }
    }

    private static String extractValue(String input, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }
}
