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
            solution = divide(cartesians[0], cartesians[1]);
            break;
        case EXP:
            solution = exponential(cartesians[0]);
            break;
        case POW:
            solution = exponential(cartesians[0], cartesians[1]);
            break;
        case LN:
            solution = logarithm(cartesians[0]);
            break;
        case LOG:
            solution = logarithm(cartesians[0], cartesians[1]);
            break;
        case SQRT:
            solution = sqrt(cartesians[0]);
            break;
        case SQ:
            solution = square(cartesians[0]);
            break;
        case SIN:
            solution = Trig.sin(cartesians[0]);
            break;
        case COS:
            solution = Trig.cos(cartesians[0]);
            break;
        case TAN:
            solution = Trig.tan(cartesians[0]);
            break;
        case SEC:
            solution = Trig.sec(cartesians[0]);
            break;
        case CSC:
            solution = Trig.csc(cartesians[0]);
            break;
        case COT:
            solution = Trig.cot(cartesians[0]);
            break;
        case ASIN:
            solution = Trig.asin(cartesians[0]);
            break;
        case ACOS:
            solution = Trig.acos(cartesians[0]);
            break;
        case ATAN:
            solution = Trig.atan(cartesians[0]);
            break;
        case ASEC:
            solution = Trig.asec(cartesians[0]);
            break;
        case ACSC:
            solution = Trig.acsc(cartesians[0]);
            break;
        case ACOT:
            solution = Trig.acot(cartesians[0]);
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
