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
 * <strong>Calculator for Elementary Complex Number Arithmetic</strong><br />
 * This project aims to perform all elementary arithmetic involving complex numbers! <br />
 * 
 * The project's interfaces supports console input or the GUI interface. 
 * (See the <a href="file:../README.md">README.md</a> on how to properly use the console I/O)<br />
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
        Window window = new Window(calc);
        window.setVisible(true);
        // psuedo-code to combine classes
    }
}
