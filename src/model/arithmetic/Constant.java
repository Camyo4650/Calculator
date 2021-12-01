/** Final CIS 200 Project
 * Cameron Pilchard
 * Eli Forssberg
 * Alex Whipple
 * 
 * This class represents 
 */

package model.arithmetic;

/**
 * 
 * 
 * @author Cameron Pilchard
 */
public class Constant extends Expression {
    protected double[] pos; // extended into complex numbers 

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
