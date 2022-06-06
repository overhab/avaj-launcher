package edu.school21.aircraft;

import edu.school21.aircraft.models.Baloon;
import edu.school21.aircraft.models.Helicopter;
import edu.school21.aircraft.models.JetPlane;
import edu.school21.coordinates.Coordinates;
import edu.school21.types.Flyable;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public abstract class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws ReflectiveOperationException {
        List<Class<?>> airCrafts = new ArrayList<>();
        airCrafts.add(JetPlane.class);
        airCrafts.add(Baloon.class);
        airCrafts.add(Helicopter.class);

        Class<?>[] param = {String.class, Coordinates.class};
        String[] types = {"JetPlane", "Baloon", "Helicopter"};

        for (int i = 0; i < 3; i++) {
            if (type.equals(types[i])) {
                Constructor<?> constructor = airCrafts.get(i).getConstructor(param);
                return (Flyable) constructor.newInstance(name, new Coordinates(longitude, latitude, height));
            }
        }

        System.out.println("[ERROR]: Wrong type");
        return null;
    }
}
