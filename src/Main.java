import model.Calculator;
import view.*;

public class Main {
    public static void main(String[] args) {
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
                e.printStackTrace();
            }
        }
    }
}
