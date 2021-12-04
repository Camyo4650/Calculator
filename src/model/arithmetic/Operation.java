/** Final CIS 200 Project
 * Cameron Pilchard
 * Eli Forssberg
 * Alex Whipple
 * 
 * This class performs operations on this expression as well as all other sub expressions 
 * in the expression "tree".
 */ 

package model.arithmetic;

import java.util.ArrayList;

/**
 * Stores multiple sub expressions. Extends the Expression abstract class.
 * Operations also store an operator, which is the defining property of how 
 * this class will calculate its result.
 * @author Eli Forssberg
 * @author Alex Whipple
 * @author Cameron Pilchard
 */
public class Operation extends Expression {
    private ArrayList<Expression> operations;
    private Operator operator;

    protected boolean isFunction = false;

    /**
     * A constructor that passes in an operator and a list of cartesian arguments
     * @param operator The Operator enum type
     * @param args Cartesian arguments
     */
    public Operation(Operator operator, double[] ... args) {
        this.operator = operator;
        this.operations = new ArrayList<>();
        for (double[] arg : args)
            this.operations.add(new Constant(arg));
    }

    /**
     * An overloaded constructor that passes in an operator and a list of sub expressions
     * @param operator The Operator enum type
     * @param args Cartesian arguments
     */
    public Operation(Operator operator, Expression ... operations) {
        this.operator = operator;
        this.operations = new ArrayList<>();
        for (Expression operation : operations)
            this.operations.add(operation);
    }
    

    /**
     * This public method solves this expression with the protected solve method 
     * and sets the output as such.
     * @param isPolar If the result should be displayed in polar coordinates
     * @return The cartesian (or polar) result
     * @throws Exception For arithmetic errors like x/0 or log 0
     */
    @Override
    public double[] solve(boolean isPolar) throws Exception {
        return isPolar ? toPolar(this.solve()) : this.solve();
    }

    /**
     * This protected method will solve this Expression using the arguments passed.
     * This method switches by the Operator enum of this Expression and calls the appropriate 
     * method defined in the {@link model.NumberSystem NumberSystem} class.
     * This method is recursive, and solves the sub expressions first before solving this Expression.
     * This is working the expression tree from the bottom up.
     * @see model.NumberSystem
     * @return The cartesian result
     * @throws ArithmeticException For arithmetic errors like x/0 or log 0
     */
    @Override
    protected double[] solve() throws Exception {
        double[][] cartesians = new double[operations.size()][2];
        for (int i = 0; i < operations.size(); i++)
            cartesians[i] = operations.get(i).solve();
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
        case MULT:
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
        case HYPOT:
            solution = hypotenuse(cartesians);
            break;
        case CIRC:
            solution = circle(cartesians[0], cartesians[1]);
            break;
        case SIN:
            solution = sin(cartesians[0]);
            break;
        case COS:
            solution = cos(cartesians[0]);
            break;
        case TAN:
            solution = tan(cartesians[0]);
            break;
        case SEC:
            solution = sec(cartesians[0]);
            break;
        case CSC:
            solution = csc(cartesians[0]);
            break;
        case COT:
            solution = cot(cartesians[0]);
            break;
        case ASIN:
            solution = asin(cartesians[0]);
            break;
        case ACOS:
            solution = acos(cartesians[0]);
            break;
        case ATAN:
            solution = atan(cartesians[0]);
            break;
        case ASEC:
            solution = asec(cartesians[0]);
            break;
        case ACSC:
            solution = acsc(cartesians[0]);
            break;
        case ACOT:
            solution = acot(cartesians[0]);
            break;
        case RAD:
            solution = toRadians(cartesians[0]);
            break;
        case DEG:
            solution = toDegrees(cartesians[0]);
            break;
        case REAL:
            solution = real(cartesians[0]);
            break;
        case IMAG:
            solution = imaginary(cartesians[0]);
            break;
        case FACT:
            solution = factorial(cartesians[0]);
            break;
        default: // not necessary since StringProcessor throws an error for invalid Operators
            break;
        }
        return solution;
    }

}
