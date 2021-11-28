/** Final CIS 200 Project
 * Cameron Pilchard
 * Eli Forssberg
 * Alex Whipple
 * 
 * This is the main "driver" class. This program utilizes the Model and View programs. 
 */

import model.Calculator;
import view.*;

/**
 * 
 * 
 * @version 0.0.1
 * @author Eli Forssberg
 * @author Alex Whipple
 * @author Cameron Pilchard
 */
public class Main {
    public static void main(String[] args) {
        boolean debug = false;
        Calculator calc = new Calculator();
        IO io = new IO();
        // psuedo-code to combine classes
        while (true) {
            try {
                String input = io.readInput();
                double[] answer = calc.calculate(input, false);
                io.displayAnswer(answer, true);
            } catch (Exception e) {
                io.displayError(e.getMessage());
                if (debug)
                    e.printStackTrace();
            }
        }
    }
}
