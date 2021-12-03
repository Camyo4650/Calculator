package model;

import java.util.ArrayList;

import model.arithmetic.*;
import view.Window;

public class Calculator {
    private ArrayList<Expression> history;
    private int searchIndex;
    private Window window;
    private StringProcessor processor;

    public Calculator() {
        this.window = new Window();
        window.setVisible(true);
        this.history = new ArrayList<>();
        this.history.add(new Constant(new double[] {0,0}));
        this.processor = new StringProcessor();
    }

    public double[] calculate(String input, boolean polar) throws Exception {
        Expression op = processor.parseExpr(input);
        history.add(op);
        return op.solve(polar);
    }

}
