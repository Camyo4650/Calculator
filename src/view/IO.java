package view;

import java.text.DecimalFormat;
import java.util.Scanner;

public class IO {

    Scanner s;

    public IO() {
        s = new Scanner(System.in);
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
            String imag = "";
            if (ans[1] < 0) imag = String.format("%.9f", ans[1]).replace("-", "-i");
            else imag = "i"+ans[1];
            System.out.println(String.format("%.9f", ans[0]) + " + " + imag);
        } else
            System.out.println(String.format("%.9f", ans[0]) + " * exp(i"+String.format("%.9f", ans[1])+")");
        System.out.println();
    }

    public void displayError(String message) {
        System.out.println("ERROR: " + message);
    }
}
