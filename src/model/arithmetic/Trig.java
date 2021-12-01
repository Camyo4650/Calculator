package model.arithmetic;

import model.NumberSystem;

public class Trig extends NumberSystem {

    protected static double[] hypotenuse(double[][] cartesians) {
        return  sqrt(
                    add(
                        square(cartesians)
                    )
                );
    }

    protected static double[] circle(double[] cartesian1, double[] cartesian2) {
        return  sqrt(
                    sub(
                        square(cartesian1)
                        ,
                        square(cartesian2)
                    )
                );
    }

    protected static double[][] square(double[][] cartesians) {
        double[][] result = new double[cartesians.length][2];
        for (int i = 0; i < cartesians.length; i++) {
            result[i] = square(cartesians[i]);
        }
        return result;
    }

    protected static double[] sin(double[] cartesian) {

        return  divide(
                    sub(
                        exponential(multiply(cartesian, new double[] {0,1}))
                        , 
                        exponential(multiply(cartesian, new double[] {0,-1}))
                    )
                    ,
                    new double[] {0,2});
    }

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

    protected static double[] sec(double[] cartesian) {
        return  divide(
                    new double[] {1,0}
                    , 
                    cos(cartesian)
                );
    }

    protected static double[] csc(double[] cartesian) {
        return  divide(
                    new double[] {1,0}
                    , 
                    sin(cartesian)
                );
    }

    protected static double[] cot(double[] cartesian) {
        return  divide(
                    new double[] {1,0}
                    , 
                    tan(cartesian)
                );
    }



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
    
    protected static double[] acos(double[] cartesian) {
        return  sub(
                    new double[] {Math.PI/2,0}
                    ,
                    asin(cartesian)
                );
    }

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

    protected static double[] asec(double[] cartesian) {
        return  acos(divide(new double[] {1,0}, cartesian));
    }

    protected static double[] acsc(double[] cartesian) {
        return  asin(divide(new double[] {1,0}, cartesian));
    }

    protected static double[] acot(double[] cartesian) {
        return  atan(divide(new double[] {1,0}, cartesian));
    }
    
    protected static double[] toDegrees(double[] cartesian) throws Exception {
        if (cartesian[1] != 0) throw new IllegalArgumentException("NUMBER MUST NOT BE COMPLEX");
        return  new double[] {Math.toDegrees(cartesian[0]), 0};
    }
    
    protected static double[] toRadians(double[] cartesian) throws Exception {
        if (cartesian[1] != 0) throw new IllegalArgumentException("NUMBER MUST NOT BE COMPLEX");
        return  new double[] {Math.toRadians(cartesian[0]), 0};
    }

}