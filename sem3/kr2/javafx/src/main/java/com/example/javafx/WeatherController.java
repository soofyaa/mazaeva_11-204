package com.example.javafx;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

public class WeatherController {
    @FXML
    private TextField display;

    public RootWeather getWeather() throws IOException {
        String city = URLEncoder.encode(display.getText(), "UTF-8");
        return new ObjectMapper().readValue(new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=5f5a9a788cc70316af3c41981ba2f54e"), RootWeather.class);
    }

    public void getWeather(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            RootWeather weather = getWeather();
            WeatherWindow.display(weather, stage);
        } catch (IOException e) {
            System.out.println("Failed to fetch weather.");
            e.printStackTrace();
        }
    }
}


