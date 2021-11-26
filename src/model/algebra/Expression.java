package model.algebra;

import model.NumberSystem;

public abstract class Expression extends NumberSystem {
    protected abstract double[] solve();
    public abstract double[] solve(boolean isPolar);
}
