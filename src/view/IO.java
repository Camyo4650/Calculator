package view;

import java.util.Scanner;

/**
 * This class possesses a field {@link java.util.Scanner#Scanner(java.io.InputStream)} that is used for console access.
 * @author []
 */
public class IO {

    Scanner s;
    private double roundOff;

    public IO() {
        s = new Scanner(System.in);
        roundOff = 1e-14;
    }

    public String readInput() {
        System.out.println("Enter equation: ");
        return s.nextLine();
    }

    public void displayAnswer(double[] ans, boolean isRect) {
        System.out.println();
        char Pi = '\u03C0';
        char Sum = '\u03A3';
        if (isRect) {
            String real = "";
            String imag = "";
            if (Math.abs(ans[0]) > roundOff) real = (""+ans[0]);
            else real = "0";
            if (ans[1] < 0) imag = " - i"+(""+ans[1]).substring(1);
            else imag = " + i"+ans[1];
            if (Math.abs(ans[1]) > roundOff) { // just to round down to zero
                System.out.println(real + imag);
            } else {
                System.out.println(real);
            }
        } else
            System.out.println(String.format("%.9f", ans[0]) + " * exp(i"+String.format("%.9f", ans[1])+")");
        System.out.println();
    }

    public void displayError(String message) {
        System.out.println();
        System.out.println("ERROR: " + message);
    }
}
