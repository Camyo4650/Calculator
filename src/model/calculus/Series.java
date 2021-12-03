package model.calculus;

import model.NumberSystem;

public class Series extends NumberSystem {
    protected Function coefficient;
    protected Function power;
    protected double[] offset;

    public Series(Function coefficient, double[] offset, Function power) {
        this.coefficient = coefficient;
        this.offset = offset;
        this.power = power;
    }

    public double[] solve(double[] x, int precision) {
        double[] ans = new double[2];
        for (int n = 0; n < precision; n++) {
            ans = add(ans, nthTerm(x, n));
        }
        return ans;
    }

    private double[] nthTerm(double[] x, int n) {
        return  multiply(
                    coefficient.solve(n)
                    ,
                    exponential(
                        sub(x, offset)
                        ,
                        power.solve(n)
                    )
                );
    }

    
}
