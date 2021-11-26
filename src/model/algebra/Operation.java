package model.algebra;

import java.util.ArrayList;

/**
 * @author Cameron Pilchard
 */
public class Operation extends Expression {
    private ArrayList<Expression> operations;
    private Operator operator;

    protected boolean isFunction = false;

    public Operation(Operator operator, double[] ... args) {
        this.operator = operator;
        this.operations = new ArrayList<>();
        for (double[] arg : args)
            this.operations.add(new Constant(arg));
    }

    public Operation(Operator operator, Expression ... operations) {
        this.operator = operator;
        this.operations = new ArrayList<>();
        for (Expression operation : operations)
            this.operations.add(operation);
    }

    @Override
    public double[] solve(boolean isPolar) throws ArithmeticException {
        return isPolar ? toPolar(this.solve()) : this.solve();
    }

    /**
     * 
     * @return
     * @throws ArithmeticException
     */
    @Override
    protected double[] solve() throws ArithmeticException {
        double[][] cartesians = new double[operations.size()][2];
        for (int i = 0; i < operations.size(); i++)
            cartesians[i] = operations.get(i).solve();
        double[][] polars = toPolar(cartesians);
        double[] solution = new double[] {0, 0};
        switch (operator) {
        case ABS:
            solution = abs(cartesians[0]);
            break;
        case ADD:
            solution = add(cartesians);
            break;
        case SUB:
            solution = sub(cartesians[0], cartesians[1]);
            break;
        case MUL:
            solution = multiply(cartesians);
            break;
        case DIV:
            solution = divide(polars[0], polars[1]);
            break;
        case EXP:
            solution = exponential(cartesians[0]);
            break;
        case POW:
            solution = exponential(polars[0], polars[1]);
            break;
        case LN:
            solution = logarithm(polars[0]);
            break;
        case LOG:
            solution = logarithm(polars[0], polars[1]);
            break;
        case DER:
            break;
        case FAC:
            break;
        case INT:
            break;
        case LIM:
            break;
        case SUM:
            break;
        default:
            break;
        }
        return solution;
    }
/*
    public double solve(double x) {
        
    }
*/
}
