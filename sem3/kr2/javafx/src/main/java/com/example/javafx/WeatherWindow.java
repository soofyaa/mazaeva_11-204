package com.example.javafx;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WeatherWindow {
    public static void display(RootWeather rootWeather, Stage stage) {
        VBox dialogVBox = new VBox(10);

        Label weatherMain = new Label("Main: " + rootWeather.getWeather().get(0).getMain());
        Label weatherDescription = new Label("Description: " + rootWeather.getWeather().get(0).getDescription());
        long tempCelsius = Math.round(rootWeather.getMain().getTemp() - 273.15);
        Label mainTemp = new Label("Temp: " + tempCelsius + "°C");
        long feelsLikeCelsius = Math.round(rootWeather.getMain().getFeels_like() - 273.15);
        Label feelsLikeTemp = new Label("Feels Like Temp: " + feelsLikeCelsius + "°C");
        long tempMinCelsius = Math.round(rootWeather.getMain().getTemp_min() - 273.15);
        Label mainTempMin = new Label("Min Temp: " + tempMinCelsius + "°C");
        long tempMaxCelsius = Math.round(rootWeather.getMain().getTemp_max() - 273.15);
        Label mainTempMax = new Label("Max Temp: " + tempMaxCelsius + "°C");
        Label mainHumidity = new Label("Humidity: " + rootWeather.getMain().getHumidity() + "%");
        Label mainPressure = new Label("Pressure: " + rootWeather.getMain().getPressure() + "hPa");
        Label windSpeed = new Label("Wind Speed: " + rootWeather.getWind().getSpeed() + "m/s");

        dialogVBox.getChildren().addAll(weatherMain, weatherDescription, mainTemp, feelsLikeTemp, mainTempMin, mainTempMax, mainHumidity, mainPressure, windSpeed);

        Scene scene = new Scene(new Group(dialogVBox), 512, 720);

        stage.setTitle("Weather Information");
        stage.setScene(scene);
        stage.show();
    }
}

