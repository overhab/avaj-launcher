package edu.school21.app;

import edu.school21.aircraft.AircraftFactory;
import edu.school21.exceptions.WrongArgumentsException;
import edu.school21.types.Flyable;

import java.io.*;

public class Program {
    public static void main(String[] args) throws IOException, WrongArgumentsException, ReflectiveOperationException {
        WeatherTower weatherTower = new WeatherTower();
        Flyable flyable = null;
        int simulations = 0;

        if (args.length != 1) {
            System.out.println("Wrong arguments");
            System.exit(-1);
        }
        Reader inputFile = null;
        try  {
            inputFile = new FileReader("src/main/java/resources/scenario.txt");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        BufferedReader bufferedReader = new BufferedReader(inputFile);

        String argument;
        String[] arguments;
        int i = 1;

        while ((argument = bufferedReader.readLine()) != null) {
            if (i == 1) {
                try {
                    simulations = Integer.parseInt(argument);
                    if (simulations <= 0) {
                        throw new WrongArgumentsException("[ERROR]: Scenario file error on line = (" + i + ")" +
                                "\nFirst argument must be positive integer");
                    }
                } catch (NumberFormatException e) {
                    Simulation.close();
                    throw new WrongArgumentsException("[ERROR]: Scenario file error on line = (" + i + ")");
                }
            } else {
                arguments = argument.split(" ");
                Validator.ValidateString(arguments, i);
                flyable = AircraftFactory.newAircraft(arguments[0],
                        arguments[1],
                        Integer.parseInt(arguments[2]),
                        Integer.parseInt(arguments[3]),
                        Integer.parseInt(arguments[4]));
                flyable.registerTower(weatherTower);
                weatherTower.register(flyable);
            }
            i++;
        }

        int count = 1;
        while (simulations != 0) {
            Simulation.LogSimulation("\n---Simulation " + count + "---");
            weatherTower.changeWeather();
            simulations--;
            count++;
        }

        Simulation.close();
        System.out.println("Simulation successful!");
    }
}
