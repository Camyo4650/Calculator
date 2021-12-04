/** Final CIS 200 Project
 * Cameron Pilchard
 * Eli Forssberg
 * Alex Whipple
 * 
 * This class is the representation of a mathematical expression.
 */ 

package model.arithmetic;

import model.NumberSystem;

/**
 * This is the abstract parent class of Operation and Constant.
 * The purpose of this class is to store data and functions for mathematical expressions.
 * This extends {@link model.NumberSystem NumberSystem} to utilize all the math functions defined there.
 * @see model.NumberSystem
 * @author Eli Forssberg
 * @author Alex Whipple
 * @author Cameron Pilchard
 */
public abstract class Expression extends NumberSystem {
    protected abstract double[] solve() throws Exception;
    public abstract double[] solve(boolean isPolar) throws Exception;
}
