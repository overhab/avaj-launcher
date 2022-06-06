package edu.school21.weather;

import edu.school21.coordinates.Coordinates;

public class WeatherProvider {

    private static WeatherProvider weatherProvider;
    private final String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }

        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return weather[(coordinates.getLatitude() + coordinates.getLongitude() + coordinates.getHeight()) % 4];
    }
}
