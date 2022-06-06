package edu.school21.types;

import edu.school21.app.WeatherTower;
import edu.school21.coordinates.Coordinates;

import java.io.IOException;

public interface Flyable {
    void updateConditions() throws IOException;
    void registerTower(WeatherTower weatherTower);

    String getName();
    long getId();
    Coordinates getCoordinates();
}
