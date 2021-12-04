/** Final CIS 200 Project
 * Cameron Pilchard
 * Eli Forssberg
 * Alex Whipple
 * 
 * This enum stores all the possible operators of this calculator.
 * Respective to the README file.
 */ 

package model.arithmetic;

/**
 * These are enums for every supported Operator by this calculator. 
 * Each enum value calls the constructor and sets a value for the number of 
 * arguments required by the function. 
 * If the argument is -1, then the Operator supports infinitely many.
 * @author Eli Forssberg
 * @author Alex Whipple
 * @author Cameron Pilchard
 */
public enum Operator {
    ABS     (1),
    ADD     (-1),
    SUB     (2),
    MULT    (-1),
    DIV     (2),
    EXP     (1),
    LN      (1),
    POW     (2),
    LOG     (2),
    SQRT    (1),
    SQ      (1),

    // Trig functions
        HYPOT   (-1),
        CIRC    (2),
        SIN     (1),
        COS     (1),
        TAN     (1),
        SEC     (1),
        CSC     (1),
        COT     (1),

        ASIN     (1),
        ACOS     (1),
        ATAN     (1),
        ASEC     (1),
        ACSC     (1),
        ACOT     (1),

    REAL    (1),
    IMAG    (1),

    DEG     (1),
    RAD     (1),
    FACT     (1);
    
    private int args;

    /**
     * This creates a new Operator enum type.
     * @param args The number of arguments supported by this Operator. 
     * If the argument is -1, then the Operator supports infinitely many.
     */
    private Operator(int args) {
        this.args = args;
    }

    /**
     * Getter method for the number of arguments supported by this Operator.
     * Used in the {@link model.StringProcessor#parseExpr(String) StringProcessor} class.
     * @see model.StringProcessor#parseExpr(String) StringProcessor#parseExpr(String)
     * @return The number of arguments supported by this Operator.
     */
    public int getArgs() {
        return this.args;
    }
}
