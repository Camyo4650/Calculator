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
     * 
     * @param expr The string form of an expression. This will be validated for console input
     * @return
     */
    public Expression parseExpr(String expr) throws Exception {
        expr = expr.replaceAll("\\s", "").replaceAll("\\n", "").replaceAll("\\t", "");

        int lowest = lowest(expr);
        Expression op = parseExpr(expr, 0, lowest);
        return op;
    }

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

    private ArrayList<String> splice(String expr) throws IllegalArgumentException {
        ArrayList<Integer> indices = delimits(expr);
        ArrayList<String> args = new ArrayList<>();
        if (indices.size() - 1 > 0) {
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

    private Expression parseExpr(String expr, int lvl, int lowest) throws Exception {
        Expression op = null;
        if (expr.contains(L+"")) {
            ArrayList<String> args = splice(expr);
            Operator operator = null;
            int totalArgs = 0;
            try {
                operator = Operator.valueOf(args.get(0).toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("INVALID OPERATOR \""+args.get(0)+"\"");
            }
            totalArgs = operator.getArgs();
            if (totalArgs > 0 && args.size() - 1 != totalArgs) {
                throw new IllegalArgumentException("FUNC "+operator+" TAKES "+totalArgs+" OPERANDS, GIVEN "+(args.size()-1));
            }

            Expression[] expressions = new Expression[args.size() - 1];
            if (lvl == lowest) {
                for (int i = 1; i < args.size(); i++) {
                    double real = 0;
                    double imag = 0;
                    if (args.get(i).indexOf(':') != -1) {
                        String[] complex = args.get(i).split(":");
                        real = Double.parseDouble(tokenize(complex[0]));
                        imag = Double.parseDouble(tokenize(complex[1]));
                    } else 
                        real = Double.parseDouble(tokenize(args.get(i)));
                    expressions[i-1] = new Constant(new double[] {real, imag});
                }
            } else {
                for (int i = 1; i < args.size(); i++) {
                    expressions[i-1] = parseExpr(args.get(i), lvl - 1, lowest);
                }
            }

            op = new Operation(operator, expressions);
        } else {
            double real = 0;
            double imag = 0;
            if (expr.indexOf(':') != -1) {
                String[] complex = expr.split(":");
                real = Double.parseDouble(tokenize(complex[0]));
                imag = Double.parseDouble(tokenize(complex[1]));
            } else 
                real = Double.parseDouble(tokenize(expr));
            op = new Constant(new double[] {real, imag});
        }
        return op;
    }
    
    public String tokenize(String input) {
        String result = input;
        String[] tokens = {
            "PI",
            "E"
        };
        double[] vals = {
            Math.PI,
            Math.E
        };
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (result.equalsIgnoreCase(token)) result = vals[i]+"";
        }
        return result;
    }
    // private String[] 
}
