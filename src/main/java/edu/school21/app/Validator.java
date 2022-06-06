package edu.school21.app;

import edu.school21.exceptions.WrongArgumentsException;

import java.io.IOException;
import java.util.Arrays;

public class Validator {

    private static final String[] TYPES = {"JetPlane", "Helicopter", "Baloon"};

    public Validator() {
    }

    public static void ValidateString(String[] line, int lineIndex) throws WrongArgumentsException, IOException {
        if (line.length != 5) {
            throw new WrongArgumentsException("[ERROR]: Scenario file error on line = (" + lineIndex + ")" +
                    "\nWrong argument format");
        }
        if (!Arrays.asList(TYPES).contains(line[0])) {
            Simulation.close();
            throw new WrongArgumentsException("[ERROR]: Scenario file error on line = (" + lineIndex + ")" +
                    "\nWrong aircraft type (Accepting: JetPlane, Helicopter, Baloon)");
        }
        try {
            int index = 2;
            int tmp;
            while (index < 5) {
                tmp = Integer.parseInt(line[index]);
                if (tmp <= 0) {
                    Simulation.close();
                    throw new WrongArgumentsException("[ERROR]: Scenario file error on line = (" + lineIndex + ")" +
                            "\n" + index + " argument must be positive integer");
                }/* else if (tmp < 0) {
                    throw new WrongArgumentsException("[ERROR]: Scenario file error on line = (" + lineIndex + ")" +
                            "\nHeight argument must be in the range of [0 - 100]");
                }*/
                index++;
            }
        } catch (NumberFormatException e) {
            Simulation.close();
            throw new WrongArgumentsException("[ERROR]: Scenario file error on line = (" + lineIndex + ")" +
                    "\nArguments must be positive integers");
        }
    }
}
