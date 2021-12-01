package model.arithmetic;

import model.NumberSystem;

public abstract class Expression extends NumberSystem {
    protected abstract double[] solve() throws Exception;
    public abstract double[] solve(boolean isPolar) throws Exception;
}
