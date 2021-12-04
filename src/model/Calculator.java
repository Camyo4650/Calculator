package model;

import model.arithmetic.*;

/**
 * This class utilizes the StringProcessor to create 
 * @author Eli Forssberg
 * @author Alex Whipple
 * @author Cameron Pilchard
 */
public class Calculator {
    private StringProcessor processor;

    public Calculator() {
        // this.history = new ArrayList<>();
        // this.history.add(new Constant(new double[] {0,0}));
        this.processor = new StringProcessor();
    }

    public double[] calculate(String input, boolean polar) throws Exception {
        Expression op = processor.parseExpr(input);
        // history.add(op);
        return op.solve(polar);
    }

}
