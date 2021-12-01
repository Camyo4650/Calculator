package model;

/**
 * These are all the corresponding functions to the Operators listed 
 * in the enumerator that take in complex numbers as cartesian coordinates 
 * represented as double arrays with length 2. This class stores static 
 * methods for calculation.
 * Every method here returns a new double array with the modified content. The original
 * double arrays passed are not modified.
 * @author Cameron Pilchard
 */
public class NumberSystem {

    /**
     * This method converts a cartesian coordinate into the polar counterpart.
     * Conversion to polar form is done by using Euler's formula.
     * This is computed by the hypotenuse of the real part and the imaginary part
     * of the cartesian coordinate, and the ordinate is the argument, or the angle
     * of the complex number.
     * @param cartesian The cartesian coordinate
     * @return Corresponding polar coordinate
     */
    public static double[] toPolar(double[] cartesian) {
        double real = cartesian[0];
        double imag = cartesian[1];
        return new double[] {Math.hypot(real, imag),  Math.atan2(imag, real)};
    }

    /**
     * This is the overloaded method of {@link this#toPolar(double[])} that converts multiple cartesian 
     * coordinates into polar coordinates simultaneously, returned 
     * in proper order.
     * Conversion to polar form is done by using Euler's formula.
     * @param cartesians All cartesian coordinates
     * @return Every corresponding polar coordinate respectively 
     */
    public static double[][] toPolar(double[] ... cartesians) {
        double[][] polars = new double[cartesians.length][2];
        for (int i = 0; i < cartesians.length; i++) {
            polars[i] = toPolar(cartesians[i]);
        }
        return polars;
    }

    /**
     * This is the inverse function of {@link this#toPolar(double[])} that converts polar coordinates into
     * cartesian coordinates.
     * Conversion to cartesian form is done by using Euler's formula.
     * @param polar The polar coordinate
     * @return Corresponding cartesian coordinate
     */
    public static double[] toCartesian(double[] polar) {
        double radius = polar[0];
        double arg = polar[1];
        return new double[] {radius * Math.cos(arg), radius * Math.sin(arg)};
    }

    /**
     * This is the overloaded method of {@link this#toCartesian(double[])} that converts multiple polar 
     * coordinates into cartesian coordinates simultaneously, returned 
     * in proper order.
     * Conversion to cartesian form is done by using Euler's formula.
     * @param polars All polar coordinates
     * @return Every corresponding cartesian coordinate respectively
     */
    public static double[][] toCartesian(double[] ... polars) {
        double[][] cartesians = new double[polars.length][2];
        for (int i = 0; i < polars.length; i++) {
            cartesians[i] = toCartesian(polars[i]);
        }
        return cartesians;
    }

    /**
     * The absolute value of any complex number.
     * The absolute value of a complex number is its radius. 
     * @param cartesian The cartesian coordinate
     * @return The radius (absolute value)
     */
    protected static double[] abs(double[] cartesian) {
        double[] polar = toPolar(cartesian);
        return new double[] {polar[0], 0};
    }

    /**
     * Returns the real number from a cartesian coordinate
     * @param cartesian The cartesian coordinate
     * @return The new 
     */
    protected static double[] real(double[] cartesian) {
        return new double[] {cartesian[0], 0};
    }

    /**
     * 
     * @param cartesian
     * @return
     */
    protected static double[] imaginary(double[] cartesian) {
        return new double[] {cartesian[1], 0};
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
     * @param cartesian1
     * @param cartesian2
     * @return
     */
    protected static double[] multiply(double[] cartesian1, double[] cartesian2) {
        double[] polar1 = toPolar(cartesian1);
        double[] polar2 = toPolar(cartesian2);
        return toCartesian(new double[] {polar1[0] * polar2[0], polar1[1] + polar2[1]});
    }

    /**
     * 
     * @param cartesians
     * @return cartesian
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
     * @param cartesian1
     * @param cartesian2
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
     * @return cartesian
     */
    protected static double[] exponential(double[] cartesian) {
        return toCartesian(new double[] {Math.exp(cartesian[0]), cartesian[1]});
    }

    /**
     * 
     * @param cartesian1
     * @param cartesian2
     * @return
     */
    protected static double[] exponential(double[] cartesian1, double[] cartesian2) {
        double[] polar1 = toPolar(cartesian1);
        double[] polar2 = toPolar(cartesian2);
        if (polar1[0] != 0) return exponential(multiply(logarithm(cartesian1), cartesian2));
        else {
            if (polar2[0] != 0 && cartesian2[1] == 0 && cartesian2[0] > 0) return new double[] {0,0};
            else throw new ArithmeticException("LOGARITHM UNDEFINED AT ZERO");
        }
    }

    /**
     * 
     * @param cartesian
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
        return divide(logarithm(cartesian2), logarithm(cartesian1));
    }

    /**
     * 
     * @param cartesian1
     * @return
     */
    protected static double[] sqrt(double[] cartesian1) {
        return exponential(cartesian1, new double[] {0.5,0});
    }

    /**
     * 
     * @param cartesian1
     * @return
     */
    protected static double[] square(double[] cartesian1) {
        return exponential(cartesian1, new double[] {2,0});
    }
}
