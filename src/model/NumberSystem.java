/** Final CIS 200 Project
 * Cameron Pilchard
 * Eli Forssberg
 * Alex Whipple
 * 
 * This class stores every method for mathematical operations and functions of the sort.
 * Some operations can be performed on complex numbers, where some can only be performed
 * on integers or simply real numbers.
 */ 

package model;

/**
 * These are all the corresponding functions to the Operators listed 
 * in the enumerator that take in complex numbers as cartesian coordinates 
 * represented as double arrays with length 2. This class stores static 
 * methods for calculation.
 * Every method here returns a new double array with the modified content. The original
 * double arrays passed are not modified.
 * @author Eli Forssberg
 * @author Alex Whipple
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
     * @return The abscissa of the complex number
     */
    protected static double[] real(double[] cartesian) {
        return new double[] {cartesian[0], 0};
    }

    /**
     * Returns the imaginary number from a cartesian coordinate
     * @param cartesian The cartesian coordinate
     * @return The ordinate of the complex number
     */
    protected static double[] imaginary(double[] cartesian) {
        return new double[] {cartesian[1], 0};
    }

    

    /**
     * Basic addition between two cartesian coordinates
     * @param cartesian1 Cartesian coordinate
     * @param cartesian2 Cartesian coordinate
     * @return The sum as a new cartesian coordinate
     */
    protected static double[] add(double[] cartesian1, double[] cartesian2) {
        return new double[] {cartesian1[0] + cartesian2[0], cartesian1[1] + cartesian2[1]};
    }

    /**
     * Basic addition supported for infinitely many coordinates (since addition operator is commutative)
     * @param cartesians Every coordinate
     * @return The sum as a new cartesian coordinate
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
     * Basic subtraction between two cartesian numbers (ordered left to right)
     * @param cartesian1 The minuend
     * @param cartesian2 The subtrahend
     * @return The difference as a new cartesian coordinate
     */
    protected static double[] sub(double[] cartesian1, double[] cartesian2) {
        return new double[] {cartesian1[0] - cartesian2[0], cartesian1[1] - cartesian2[1]};
    }

    /**
     * Basic multiplication between two cartesian coordinates
     * @param cartesian1 Cartesian coordinate
     * @param cartesian2 Cartesian coordinate
     * @return The product as a new cartesian coordinate
     */
    protected static double[] multiply(double[] cartesian1, double[] cartesian2) {
        double[] polar1 = toPolar(cartesian1);
        double[] polar2 = toPolar(cartesian2);
        return toCartesian(new double[] {polar1[0] * polar2[0], polar1[1] + polar2[1]});
    }

    /**
     * Basic multiplication supported for infinitely many coordinates (since multiplication operator is commutative)
     * @param cartesians Every coordinate
     * @return The product as a new cartesian coordinate
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
     * Basic division between two cartesian coordinates
     * @param cartesian1 The dividend
     * @param cartesian2 The divisor
     * @return The quotient as a new cartesian coordinate
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
     * Basic exponential function (e^x where e is the base of the natural log)
     * @param cartesian The power
     * @return The result as a new cartesian coordinate
     */
    protected static double[] exponential(double[] cartesian) {
        return toCartesian(new double[] {Math.exp(cartesian[0]), cartesian[1]});
    }

    /**
     * Basic exponentiation (a^x) between two cartesian coordinates
     * @param cartesian1 The base
     * @param cartesian2 The power
     * @return The result as a new cartesian coordinate
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
     * Natural logarithm (base e, Napier's constant or Euler's number)
     * @param cartesian The argument
     * @return The result as a new cartesian coordinate
     */
    protected static double[] logarithm(double[] cartesian) throws ArithmeticException {
        double[] polar = toPolar(cartesian);
        if (polar[0] == 0) throw new ArithmeticException("LOGARITHM UNDEFINED AT ZERO");
        return new double[] {Math.log(polar[0]), polar[1]};
    }

    /**
     * Logarithm with a custom base between two cartesian coordinates
     * @param cartesian1 The base
     * @param cartesian2 The argument
     * @return The result as a new cartesian coordinate
     */
    protected static double[] logarithm(double[] cartesian1, double[] cartesian2) {
        return divide(logarithm(cartesian2), logarithm(cartesian1));
    }

    /**
     * The shortcut for the square root of a cartesian coordinate
     * @param cartesian1 The argument
     * @return The result as a new cartesian coordinate
     */
    protected static double[] sqrt(double[] cartesian1) {
        return exponential(cartesian1, new double[] {0.5,0});
    }

    /**
     * The shortcut for the cartesian coordinate times itself
     * @param cartesian1 The argument 
     * @return The result as a new cartesian coordinate
     */
    protected static double[] square(double[] cartesian1) {
        return exponential(cartesian1, new double[] {2,0});
    }

    /**
     * The shortcut to find the hypotenuse between infinite arguments
     * @param cartesians Every coordinate
     * @return The result as a new cartesian coordinate
     */
    protected static double[] hypotenuse(double[][] cartesians) {
        return  sqrt(
                    add(
                        square(cartesians)
                    )
                );
    }

    /**
     * The shortcut to find the coordinates of a circle √(r^2 - x^2)
     * @param cartesian1 The radius
     * @param cartesian2 The argument
     * @return The result as a new cartesian coordinate
     */
    protected static double[] circle(double[] cartesian1, double[] cartesian2) {
        return  sqrt(
                    sub(
                        square(cartesian1)
                        ,
                        square(cartesian2)
                    )
                );
    }

    /**
     * The shortcut to find the square (x^2) of multiple cartesian coordinates
     * @param cartesians The coordinates
     * @return The squares in their respective order
     */
    protected static double[][] square(double[] ... cartesians) {
        double[][] result = new double[cartesians.length][2];
        for (int i = 0; i < cartesians.length; i++) {
            result[i] = square(cartesians[i]);
        }
        return result;
    }

    /**
     * The shortcut to find the sin value of a cartesian coordinate. Derived from Euler's formula.
     * @param cartesian The cartesian coordinate
     * @return The result as a new cartesian coordinate
     */
    protected static double[] sin(double[] cartesian) {
        //
        return  divide(
                    sub(
                        exponential(multiply(cartesian, new double[] {0,1}))
                        , 
                        exponential(multiply(cartesian, new double[] {0,-1}))
                    )
                    ,
                    new double[] {0,2}
                );
    }

    /**
     * The shortcut to find the cos value of a cartesian coordinate. Derived from Euler's formula.
     * @param cartesian The cartesian coordinate
     * @return The result as a new cartesian coordinate
     */
    protected static double[] cos(double[] cartesian) {
        return  divide(
                    add(
                        exponential(multiply(cartesian, new double[] {0,1}))
                        , 
                        exponential(multiply(cartesian, new double[] {0,-1}))
                    )
                    ,
                    new double[] {2,0}
                );
    }

    /**
     * The shortcut to find the tan value of a cartesian coordinate. Derived from Euler's formula.
     * @param cartesian The cartesian coordinate
     * @return The result as a new cartesian coordinate
     */
    protected static double[] tan(double[] cartesian) {
        return  multiply(
                    divide(
                        sub(
                            exponential(multiply(cartesian, new double[] {0,1}))
                            , 
                            exponential(multiply(cartesian, new double[] {0,-1}))
                        )
                        , 
                        add(
                            exponential(multiply(cartesian, new double[] {0,1}))
                            , 
                            exponential(multiply(cartesian, new double[] {0,-1}))
                        )
                    ),
                    new double[] {0,-1}
                );
    }

    /**
     * The shortcut to find the tan value of a cartesian coordinate. 1/cos(x)
     * @param cartesian The cartesian coordinate
     * @return The result as a new cartesian coordinate
     */
    protected static double[] sec(double[] cartesian) {
        return  divide(
                    new double[] {1,0}
                    , 
                    cos(cartesian)
                );
    }

    /**
     * The shortcut to find the tan value of a cartesian coordinate. 1/sin(x)
     * @param cartesian The cartesian coordinate
     * @return The result as a new cartesian coordinate
     */
    protected static double[] csc(double[] cartesian) {
        return  divide(
                    new double[] {1,0}
                    , 
                    sin(cartesian)
                );
    }

    /**
     * The shortcut to find the tan value of a cartesian coordinate. 1/tan(x)
     * @param cartesian The cartesian coordinate
     * @return The result as a new cartesian coordinate
     */
    protected static double[] cot(double[] cartesian) {
        return  divide(
                    new double[] {1,0}
                    , 
                    tan(cartesian)
                );
    }

    /**
     * The shortcut to find the inverse sin value of a cartesian coordinate. Derived from Euler's formula.
     * @param cartesian The cartesian coordinate
     * @return The result as a new cartesian coordinate
     */
    protected static double[] asin(double[] cartesian) {
        return  multiply(
                    new double[] {0,1}
                    , 
                    logarithm(
                        sub(
                            sqrt(
                                sub(
                                    new double[] {1,0}
                                    , 
                                    square(cartesian)
                                )
                            )
                            ,
                            multiply(
                                cartesian
                                , 
                                new double[] {0,1}
                            )
                        )
                    )
                );
    }
    
    /**
     * The shortcut to find the inverse cos value of a cartesian coordinate. Derived from Euler's formula.
     * @param cartesian The cartesian coordinate
     * @return The result as a new cartesian coordinate
     */
    protected static double[] acos(double[] cartesian) {
        return  sub(
                    new double[] {Math.PI/2,0}
                    ,
                    asin(cartesian)
                );
    }

    /**
     * The shortcut to find the inverse tan value of a cartesian coordinate. Derived from Euler's formula.
     * @param cartesian The cartesian coordinate
     * @return The result as a new cartesian coordinate
     */
    protected static double[] atan(double[] cartesian) {
        return  multiply(
                    new double[] {0, -0.5}
                    ,
                    sub(
                        logarithm(
                            sub(
                                new double[] {0,1}
                                , 
                                cartesian
                            )
                        )
                        ,
                        logarithm(
                            add(
                                new double[] {0,1}
                                , 
                                cartesian
                            )
                        )
                    )
                );
    }

    /**
     * The shortcut to find the inverse sec value of a cartesian coordinate. Derived from Euler's formula.
     * @param cartesian The cartesian coordinate
     * @return The result as a new cartesian coordinate
     */
    protected static double[] asec(double[] cartesian) {
        return  acos(divide(
                    new double[] {1,0}
                    , 
                    cartesian
                ));
    }

    /**
     * The shortcut to find the inverse csc value of a cartesian coordinate. Derived from Euler's formula.
     * @param cartesian The cartesian coordinate
     * @return The result as a new cartesian coordinate
     */
    protected static double[] acsc(double[] cartesian) {
        return  asin(divide(
                    new double[] {1,0}
                    , 
                    cartesian
                ));
    }

    /**
     * The shortcut to find the inverse cot value of a cartesian coordinate. Derived from Euler's formula.
     * @param cartesian The cartesian coordinate
     * @return The result as a new cartesian coordinate
     */
    protected static double[] acot(double[] cartesian) {
        return  atan(divide(
                    new double[] {1,0}
                    , 
                    cartesian
                ));
    }
    
    /**
     * Converts a real number from radians to degrees
     * @param cartesian The real number
     * @return The result
     * @throws Exception Thrown when the number is complex
     */
    protected static double[] toDegrees(double[] cartesian) throws Exception {
        if (cartesian[1] != 0) throw new IllegalArgumentException("NUMBER MUST NOT BE COMPLEX");
        return  new double[] {Math.toDegrees(cartesian[0]), 0};
    }
    
    /**
     * Converts a real number from degrees to radians
     * @param cartesian The real number
     * @return The result
     * @throws Exception Thrown when the number is complex
     */
    protected static double[] toRadians(double[] cartesian) throws Exception {
        if (cartesian[1] != 0) throw new IllegalArgumentException("NUMBER MUST NOT BE COMPLEX");
        return  new double[] {Math.toRadians(cartesian[0]), 0};
    }
    
    /**
     * Finds the factorial of an integer (real only)
     * @param cartesian The integer number
     * @return The result
     * @throws Exception Thrown when the number is not real and/or is not an integer
     */
    protected static double[] factorial(double[] cartesian) throws Exception {
    	double[] facAnswer = {1.0, 0.0};
    	//System.out.println(cartesian[0] + " " + cartesian[1]);
    	if(cartesian[0] < 0) {
    		throw new IllegalArgumentException("NUMBER MUST BE POSITIVE");
    	}
    	if(cartesian[0]%1 != 0) {
    		throw new IllegalArgumentException("NUMBER MUST BE AN INTEGER");
    	}
        if (cartesian[1] != 0) {
            throw new IllegalArgumentException("NUMBER MUST BE REAL");
        }
    	for(int i = 1; i <= cartesian[0]; i++) {
    		facAnswer[0] = facAnswer[0] * i;
    	}
    	return facAnswer;
    }

}
