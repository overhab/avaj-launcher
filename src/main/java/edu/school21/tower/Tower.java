package edu.school21.tower;

import edu.school21.app.Simulation;
import edu.school21.types.Flyable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
    private final List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) throws IOException {
        observers.add(flyable);
        /*System.out.println("Tower says: "
                + flyable.getClass().getSimpleName()
                + "#" + flyable.getName()
                + "(" + flyable.getId() + ") registered to weather tower.");*/
        Simulation.LogSimulation("Tower says: "
                + flyable.getClass().getSimpleName()
                + "#" + flyable.getName()
                + "(" + flyable.getId() + ") registered to weather tower.");
    }

    public void unregister(Flyable flyable) throws IOException {
        observers.remove(flyable);
        /*System.out.println("Tower says: "
                + flyable.getClass().getSimpleName()
                + "#" + flyable.getName()
                + "(" + flyable.getId() + ") unregistered to weather tower.");*/
        Simulation.LogSimulation("Tower says: "
                + flyable.getClass().getSimpleName()
                + "#" + flyable.getName()
                + "(" + flyable.getId() + ") unregistered to weather tower. Coordinates: ["
                + flyable.getCoordinates().getLongitude() + ", "
                + flyable.getCoordinates().getLatitude() + ", "
                + flyable.getCoordinates().getHeight() + "]");
    }

    protected void conditionsChanged() throws IOException {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }
}
