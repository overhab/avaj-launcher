package edu.school21.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Simulation {
    private static BufferedWriter WRITER = null;

    static {
        try {
            WRITER = new BufferedWriter(new FileWriter("simulation.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Simulation() {
    }

    public static void LogSimulation(String string) throws IOException {
        WRITER.write(string + "\n");
    }

    public static void close() throws IOException {
        WRITER.close();
    }
}
