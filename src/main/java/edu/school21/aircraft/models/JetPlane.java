package edu.school21.aircraft.models;

import edu.school21.aircraft.Aircraft;
import edu.school21.app.Simulation;
import edu.school21.coordinates.Coordinates;
import edu.school21.app.WeatherTower;
import edu.school21.types.Flyable;

import java.io.IOException;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    @Override
    public void updateConditions() throws IOException {
        String weather = weatherTower.getWeather(coordinates);
        if (weather.equals("RAIN")) {
            Simulation.LogSimulation("JetPlane#" + name + "(" + id + "): raining...");
            coordinates.setLatitude(coordinates.getLatitude() + 5);
        } else if (weather.equals("FOG")) {
            Simulation.LogSimulation("JetPlane#" + name + "(" + id + "): fog, can't see");
            coordinates.setLatitude(coordinates.getLatitude() + 1);
        } else if (weather.equals("SUN")) {
            Simulation.LogSimulation("JetPlane#" + name + "(" + id + "): sunny");
            coordinates.setLatitude(coordinates.getLatitude() + 10);
            int newHeight = coordinates.getHeight() + 2;
            newHeight = Math.min(newHeight, 100);
            coordinates.setHeight(newHeight);
        } else if (weather.equals("SNOW")) {
            Simulation.LogSimulation("JetPlane#" + name + "(" + id + "): snow");
            int newHeight = coordinates.getHeight() - 7;
            newHeight = Math.max(newHeight, 0);
            coordinates.setHeight(newHeight);
            if (newHeight == 0) {
                weatherTower.unregister(this);
            }
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }

    public JetPlane(String name, Coordinates coordinates) {
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
