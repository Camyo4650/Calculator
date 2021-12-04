/** Final CIS 200 Project
 * Cameron Pilchard
 * Eli Forssberg
 * Alex Whipple
 * 
 * This class represents the mathematical constant of a cartesian or polar coordinate (complex number)
 */

package model.arithmetic;

/**
 * Acts as a mathematical constant with only a real part and an imaginary part.
 * 
 * @author Eli Forssberg
 * @author Alex Whipple
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
