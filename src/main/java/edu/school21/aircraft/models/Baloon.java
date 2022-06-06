package edu.school21.aircraft.models;

import edu.school21.aircraft.Aircraft;
import edu.school21.app.Simulation;
import edu.school21.coordinates.Coordinates;
import edu.school21.app.WeatherTower;
import edu.school21.types.Flyable;

import java.io.IOException;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    @Override
    public void updateConditions() throws IOException {
        String weather = weatherTower.getWeather(coordinates);
        if (weather.equals("RAIN")) {
            Simulation.LogSimulation("Baloon#" + name + "(" + id + "): raining...");
            coordinates.setHeight(coordinates.getHeight() - 5);
        } else if (weather.equals("FOG")) {
            Simulation.LogSimulation("Baloon#" + name + "(" + id + "): fog, can't see");
            coordinates.setHeight(coordinates.getHeight() - 3);
        } else if (weather.equals("SUN")) {
            Simulation.LogSimulation("Baloon#" + name + "(" + id + "): sunny");
            coordinates.setLongitude(coordinates.getLongitude() + 2);
            int newHeight = coordinates.getHeight() + 4;
            newHeight = Math.min(newHeight, 100);
            coordinates.setHeight(newHeight);
        } else if (weather.equals("SNOW")) {
            Simulation.LogSimulation("Baloon#" + name + "(" + id + "): snow");
            int newHeight = coordinates.getHeight() - 15;
            newHeight = Math.max(newHeight, 0);
            coordinates.setHeight(newHeight);
        }
        if (coordinates.getHeight() == 0) {
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public String getName() {
        return this.name;
    }

    public long getId() {
        return this.id;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }
}
