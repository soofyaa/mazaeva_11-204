package ru.itis.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RandomCocktailReceiver {
    private static final String API_URL = "https://www.thecocktaildb.com/api/json/v1/1/random.php";

    @SneakyThrows
    public static Cocktail getRandomCocktail() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (InputStream inputStream = connection.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            String json = response.toString();
            System.out.println("Received JSON: " + json);

            Cocktail cocktail = new ObjectMapper().readValue(json, Cocktail.class);
            System.out.println("Parsed Cocktail: " + cocktail);

            return cocktail;
        } finally {
            connection.disconnect();
        }
    }
}
