package edu.school21.app;

import edu.school21.coordinates.Coordinates;
import edu.school21.tower.Tower;
import edu.school21.weather.WeatherProvider;

import java.io.IOException;

public class WeatherTower extends Tower {

    private final WeatherProvider weatherProvider = WeatherProvider.getProvider();

    public WeatherTower() {
    }

    public String getWeather(Coordinates coordinates) {
        return weatherProvider.getCurrentWeather(coordinates);
    }

    void changeWeather() throws IOException {
        conditionsChanged();
    }
}
