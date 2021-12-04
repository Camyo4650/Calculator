/** Final CIS 200 Project
 * Cameron Pilchard
 * Eli Forssberg
 * Alex Whipple
 * 
 * This class will parse String input in preparation of computation.  
 */

package model;

import java.util.ArrayList;

import model.arithmetic.Constant;
import model.arithmetic.Expression;
import model.arithmetic.Operation;
import model.arithmetic.Operator;

/**
 * This class contains three fields that represent delimiters:<br />
 *  L : the left  bracket delimiter<br />
 *  D : the delimiting separator of arguments<br />
 *  R : the right bracket delimiter<br />
 * This format allows this parser to easily digest and calculate with ease.
 * @author Eli Forssberg
 * @author Alex Whipple
 * @author Cameron Pilchard
 */
public class StringProcessor {
    private char L; // delimiting left bracket
    private char D; // delimiter
    private char R; // delimiting right bracket

    public StringProcessor() {
        this.L = '(';
        this.D = ',';
        this.R = ')';
    }

    public StringProcessor(char left, char right, char delimiter) {
        this.L = left;
        this.R = right;
        this.D = delimiter;
    }

    /**
     * This function will remove all whitespace from the expression.
     * The depth of the Lisp tree nest is  
     * @param expr The string form of an expression. This will be validated for console input
     * @return
     */
    public Expression parseExpr(String expr) throws Exception {
        expr = expr.replaceAll("\\s", "").replaceAll("\\n", "").replaceAll("\\t", "");

        int lowest = lowest(expr);
        Expression op = parseExpr(expr, 0, lowest);
        return op;
    }

    /**
     * This function will see how deeply nested the Expression is.
     * The expression is essentially a tree of other Expressions. 
     * This function finds the bottom of the tree. 
     * This comes in handy when finding functions with multiple arguments, 
     * because the bottom of the tree has to be Constants only.
     * @param expr The string representation of the expression
     * @return The depth of the expression tree
     * @throws IllegalArgumentException Passed when the level does not end at zero (the expected top).
     * This indicates that the expression is not properly closed.
     */
    private int lowest(String expr) throws IllegalArgumentException {
        int lvl = 0;
        int lowest = 0;
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == L) lvl--; // every left  bracket, lower the level
            if (expr.charAt(i) == R) lvl++; // every right bracket, raise the level
            
            if (lvl < lowest) lowest = lvl; // save the lowest level

            // error handling
            if (lvl > 0) throw new IllegalArgumentException("MISPLACED TOKEN \""+R+"\", "+(i+1));
        }
        if (lvl < 0) throw new IllegalArgumentException("INCOMPLETE EXPRESSION, CHECK INPUT");
        return lowest + 1;
    }

    /**
     * This function is delimited by the fields L, D, and R. 
     * L represents a left bracket, 
     * D represents the delimiter between arguments of a function, and
     * R represents a right bracket.
     * 
     * This delimits the top most function, like add in add(2,3,mult(5,7)).
     * @param expr The string representation of the expression
     * @return The indices of delimiters (D) and the right-most closing bracket (R)
     */
    private ArrayList<Integer> delimits(String expr) {
        int lvl = 0;
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == L) lvl--;
            if (expr.charAt(i) == R) lvl++;
            if (expr.charAt(i) == D && lvl == -1) indices.add(i);
        }
        indices.add(expr.length()-1);
        return indices;
    }

    /**
     * This function splices each argument into an arraylist and includes the name of 
     * the function. This allows the interpreter to perform the expected calculation.
     * @param expr The string representation of the expression
     * @return Each expression with the name of the function as the first element
     * @throws IllegalArgumentException
     */
    private ArrayList<String> splice(String expr) throws IllegalArgumentException {
        ArrayList<Integer> indices = delimits(expr);
        ArrayList<String> args = new ArrayList<>();
        if (indices.size() - 1 > 0) { // checks if there are delimiters (D) inside the function
            indices.add(0, expr.indexOf(L));
            args.add(expr.substring(0, expr.indexOf(L)));
            for (int i = 0; i < indices.size() - 1; i++) {
                String e = expr.substring(indices.get(i) + 1, indices.get(i + 1));
                args.add(e);
            }
        } else {
            args.add(expr.substring(0, expr.indexOf(L)));
            String e = expr.substring(expr.indexOf(L) + 1, expr.length() - 1);
            if (e.length() == 0) throw new IllegalArgumentException("EMPTY FUNC " + args.get(0));
            args.add(e);
        }
        return args;
    }

    /**
     * This function navigates and parses the entire expression tree into an Expression 
     * class. 
     * This function determines whether or not the argument is 
     * its own Expression or is a Constant. It does this by splicing the expression 
     * into sub Expressions that could either be a constant or a function. 
     * This function recurs for each "sub function", like the mult in add(2,3,mult(5,7))
     * @param expr The string representation of the function
     * @param lvl The depth into the tree (determined by the number of left bracket delimiters (L) )
     * @param lowest The lowest depth of the tree
     * @return The Expression class that can be used for calculations
     * @throws Exception Passed from splicing and lowest methods. 
     * This is also called whenever a function is called and has an invalid operator token 
     * or does not have the required amount of arguments (if it requires such).
     */
    private Expression parseExpr(String expr, int lvl, int lowest) throws Exception {
        Expression op = null;
        if (expr.contains(L+"")) { // if the sub expression contains a left (L) bracket delimiter
            ArrayList<String> args = splice(expr); // all the arguments with the function name as the first element
            Operator operator = null; // Since there is a left bracket (L), this is a sub expression which requires
            // an Operator class that resembles its own, independent expression
            try {
                operator = Operator.valueOf(args.get(0).toUpperCase()); // Tries to get the Enum by the function called
                // in args.get(0), i.e. the first element of the args arraylist
                // If no error is thrown, then this is a valid Operator.
            } catch (IllegalArgumentException e) {
                // This is thrown if the function is invalid. Therefore, this is not a valid Operator.
                throw new IllegalArgumentException("INVALID OPERATOR \""+args.get(0)+"\""); 
            }
            int totalArgs = operator.getArgs(); // this is the total amount of arguments the function needs
            // If totalArgs is -1, then the operator supports infinite arguments. Otherwise, the function
            // only accepts "totalArgs" alone.
            if (totalArgs > 0 && args.size() - 1 != totalArgs) {
                throw new IllegalArgumentException("FUNC "+operator+" TAKES "+totalArgs+" OPERANDS, GIVEN "+(args.size()-1));
                // This is thrown when the number of arguments is not supported by the function called.
            }

            // Now we get to the sub expressions.
            Expression[] expressions = new Expression[args.size() - 1];
            if (lvl == lowest) {
                // If we are at the bottom of the expression tree, then each argument must be a Constant
                for (int i = 1; i < args.size(); i++) {
                    double real = 0;
                    double imag = 0;
                    if (args.get(i).indexOf(':') != -1) { // splits by the imaginary delimiter ":" if it is present
                        String[] complex = args.get(i).split(":");
                        real = Double.parseDouble(tokenize(complex[0])); // left part is real
                        imag = Double.parseDouble(tokenize(complex[1])); // right part is imaginary
                    } else 
                        real = Double.parseDouble(tokenize(args.get(i)));
                    // Creates a new Constant for each argument respectively
                    expressions[i-1] = new Constant(new double[] {real, imag});
                }
            } else {
                // If we are NOT at the bottom of the expression tree, then the argument might not be a Constant.
                // This is because we already tested if there is an (L) bracket. Therefore, we know that this
                // is a sub expression.
                for (int i = 1; i < args.size(); i++) {
                    expressions[i-1] = parseExpr(args.get(i), lvl - 1, lowest);
                }
            }

            // The final result is instantiated
            op = new Operation(operator, expressions);
        } else {
            // If there is NO (L) bracket, then this argument is a Constant.
            double real = 0;
            double imag = 0;
            if (expr.indexOf(':') != -1) { // splits by the imaginary delimiter ":" if it is present
                String[] complex = expr.split(":");
                real = Double.parseDouble(tokenize(complex[0])); // left part is real
                imag = Double.parseDouble(tokenize(complex[1])); // right part is imaginary
            } else 
                real = Double.parseDouble(tokenize(expr));
            
                // The final result is instantiated
            op = new Constant(new double[] {real, imag});
        }
        // return the result
        return op;
    }
    
    /**
     * Replaces an argument if it contains tokens "PI" or "E", not case sensitive.
     * Also, it is important that the tokens are just left by themselves: "3pi" 
     * does not yield 3*Math.PI and will throw an error. Instead, use the MULT function 
     * to multiply 3*pi or mult(3,pi)
     * @param input The argument of a function
     * @return The token value, otherwise the input is returned.
     */
    public String tokenize(String input) {
        String result = input;
        String[] tokens = { // each token
            "PI",
            "E"
        };
        double[] vals = { // listed respectively
            Math.PI,
            Math.E
        };
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (result.equalsIgnoreCase(token)) result = vals[i]+""; // replace with value if argument is a token
        }
        return result;
    }
}
