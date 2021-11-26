package model.algebra;

import model.NumberSystem;

public class Trig extends NumberSystem {
    public static double[] sin(double[] cartesian) {
        return  divide(
                    sub(
                        exponential(multiply(cartesian, new double[] {0,1}))
                        , 
                        exponential(multiply(cartesian, new double[] {0,-1}))
                    )
                    ,
                    new double[] {0,2});
    }

    public static double[] cos(double[] cartesian) {
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

    public static double[] tan(double[] cartesian) {
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

    public static double[] sec(double[] cartesian) {
        return  divide(
                    new double[] {1,0}
                    , 
                    cos(cartesian)
                );
    }

    public static double[] csc(double[] cartesian) {
        return  divide(
                    new double[] {1,0}
                    , 
                    sin(cartesian)
                );
    }

    public static double[] cot(double[] cartesian) {
        return  divide(
                    new double[] {1,0}
                    , 
                    tan(cartesian)
                );
    }



    public static double[] asin(double[] cartesian) {
        return  multiply(
                    new double[] {0,1}
                    , 
                    logarithm(
                        add(
                            sqrt(
                                sub(
                                    new double[] {-1,0}
                                    , 
                                    cartesian
                                )
                            )
                            ,
                            multiply(
                                cartesian
                                , 
                                new double[] {0,-1}
                            )
                        )
                    )
                );
    }
    
    public static double[] acos(double[] cartesian) {
        return  sub(
                    new double[] {Math.PI/2,0}
                    ,
                    asin(cartesian)
                );
    }

    public static double[] atan(double[] cartesian) {
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
    
}
