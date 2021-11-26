package model.algebra;

public class Constant extends Expression {
    protected double[] pos; // extended to the complex

    public Constant(double[] cartesian) {
        this.pos = cartesian;
    }

    @Override
    protected double[] solve() {
        return this.pos;
    }

    @Override
    public double[] solve(boolean isPolar) {
        return isPolar ? toPolar(this.pos) : this.pos;
    }
    
}
