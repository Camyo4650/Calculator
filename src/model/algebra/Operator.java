package model.algebra;

public enum Operator {
    ABS     (1),
    ADD     (-1),
    SUB     (2),
    MUL     (-1),
    DIV     (2),
    EXP     (1),
    LN      (1),
    POW     (2),
    LOG     (2),

    // Trig functions
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

    LIM     (1),
    DER     (1),
    INT     (1),
    SUM     (1),
    FAC     (1);
    
    private int args;
    private int argsMax;

    private Operator(int args) {
        this.args = args;
        this.argsMax = args;
    }

    private Operator(int args, int argsMax) {
        this.args = args;
        this.argsMax = argsMax;
    }

    public int getArgs() {
        return this.args;
    }

    public int getMaxArgs() {
        return this.argsMax;
    }
}
