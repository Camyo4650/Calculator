package model;

public class NumberSystem {

    /**
     * 
     * @param cartesian
     * @return
     */
    public static double[] toPolar(double[] cartesian) {
        double real = cartesian[0];
        double imag = cartesian[1];
        return new double[] {Math.hypot(real, imag),  Math.atan2(imag, real)};
    }

    /**
     * 
     * @param cartesians
     * @return
     */
    public static double[][] toPolar(double[] ... cartesians) {
        double[][] polars = new double[cartesians.length][2];
        for (int i = 0; i < cartesians.length; i++) {
            polars[i] = toPolar(cartesians[i]);
        }
        return polars;
    }

    /**
     * 
     * @param polar
     * @return
     */
    public static double[] toCartesian(double[] polar) {
        double radius = polar[0];
        double arg = polar[1];
        return new double[] {radius * Math.cos(arg), radius * Math.sin(arg)};
    }

    /**
     * 
     * @param polars
     * @return
     */
    public static double[][] toCartesian(double[] ... polars) {
        double[][] cartesians = new double[polars.length][2];
        for (int i = 0; i < polars.length; i++) {
            cartesians[i] = toCartesian(polars[i]);
        }
        return cartesians;
    }

    /**
     * 
     * @param cartesian
     * @return
     */
    protected static double[] abs(double[] cartesian) {
        return new double[] {Math.abs(cartesian[0]) + Math.abs(cartesian[1]), 0};
    }

    /**
     * 
     * @param cartesian1
     * @param cartesian2
     * @return
     */
    protected static double[] add(double[] cartesian1, double[] cartesian2) {
        return new double[] {cartesian1[0] + cartesian2[0], cartesian1[1] + cartesian2[1]};
    }

    /**
     * 
     * @param cartesians
     * @return
     */
    protected static double[] add(double[] ... cartesians) {
        double[] result = cartesians[0];
        for (int i = 1; i < cartesians.length; i++) {
            result[0] += cartesians[i][0];
            result[1] += cartesians[i][1];
        }
        return result;
    }

    /**
     * 
     * @param cartesian1
     * @param cartesian2
     * @return
     */
    protected static double[] sub(double[] cartesian1, double[] cartesian2) {
        return new double[] {cartesian1[0] - cartesian2[0], cartesian1[1] - cartesian2[1]};
    }

    /**
     * 
     * @param polar1
     * @param polar2
     * @return
     */
    protected static double[] multiply(double[] cartesian1, double[] cartesian2) {
        double[] polar1 = toPolar(cartesian1);
        double[] polar2 = toPolar(cartesian2);
        return toCartesian(new double[] {polar1[0] * polar2[0], polar1[1] + polar2[1]});
    }

    /**
     * 
     * @param polars
     * @return
     */
    protected static double[] multiply(double[] ... cartesians) {
        double[][] polars = toPolar(cartesians);
        double[] result = polars[0];
        for (int i = 1; i < polars.length; i++) {
            result[0] *= polars[i][0];
            result[1] += polars[i][1];
        }
        return toCartesian(result);
    }

    /**
     * 
     * @param polar1
     * @param polar2
     * @return
     */
    protected static double[] divide(double[] cartesian1, double[] cartesian2) {
        double[] polar1 = toPolar(cartesian1);
        double[] polar2 = toPolar(cartesian2);
        if (polar2[0] == 0) { // if the radius is zero, this means the number is zero itself which is NOT divisible
            throw new ArithmeticException("INDIVISIBLE BY ZERO");
        } else {
            return toCartesian(new double[] {polar1[0] / polar2[0], polar1[1] - polar2[1]}); // property of exponents
            /*
            The way this works is by dividing r0 * exp(i * a0) / r1 * exp(i * a1)
            By the law of exponents and some simplification, this is equal to:
            (r0 / r1) * exp(i * (a0 - a1))
            So the radius = r0/r1 and the argument is a0-a1
            */
        }
    }

    /**
     * 
     * @param cartesian
     * @return polar
     */
    protected static double[] exponential(double[] cartesian) {
        return toCartesian(new double[] {Math.exp(cartesian[0]), cartesian[1]});
    }

    /**
     * 
     * @param polar1
     * @param polar2
     * @return
     */
    protected static double[] exponential(double[] cartesian1, double[] cartesian2) {
        return exponential(multiply(logarithm(cartesian1), cartesian2));
    }

    /**
     * 
     * @param polar
     * @return cartesian
     */
    protected static double[] logarithm(double[] cartesian) throws ArithmeticException {
        double[] polar = toPolar(cartesian);
        if (polar[0] == 0) throw new ArithmeticException("LOGARITHM UNDEFINED AT ZERO");
        return new double[] {Math.log(polar[0]), polar[1]};
    }

    /**
     * 
     * @param cartesian1
     * @param cartesian2
     * @return
     */
    protected static double[] logarithm(double[] cartesian1, double[] cartesian2) {
        return divide(logarithm(cartesian1), logarithm(cartesian2));
    }

    protected static double[] sqrt(double[] cartesian1) {
        return exponential(cartesian1, new double[] {0.5,0});
    }
}
