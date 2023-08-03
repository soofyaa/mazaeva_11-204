import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.*;
import java.net.*;

public class Main {

    public static void main(String[] args) throws IOException {
        URLConnection urlConnection =
                new URL("https://api.openweathermap.org/data/2.5/weather?q=Astrakhan&appid=209412395c0806ea38f6accb1a3b49de").openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
        InputStream inputStream = urlConnection.getInputStream();
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);


        String json = new String(bytes);
        CityWeather cityWeathers = new ObjectMapper().readValue(json, new TypeReference<CityWeather>(){});
        System.out.println(cityWeathers);
        int temp = (int) (cityWeathers.main.temp - 273.15);
        int feel = (int) (cityWeathers.main.feels_like - 273.15);
        System.out.println("Привет, погода в " + cityWeathers.name + " сейчас: " +
                temp + " ощущается как " + feel +
                ", ветер со скоростью " + cityWeathers.wind.speed + " и влажность " + cityWeathers.main.humidity);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CityWeather {
        public String name;
        public Wind wind;
        public Mainn main;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Mainn {
        public Double temp;
        public Double feels_like;
        public Integer humidity;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Wind {
        public Double speed;
    }
}
