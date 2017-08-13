package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.11
 * Write a program EvaluatePostfix that takes a postfix expression from standard input,
 * evaluates it, and prints the value.
 * (Piping the output of your program from the previous exercise to this program gives
 * equivalent behavior to Evaluate.)
 */
public class Exercise_1_3_11 {
    public static String evaluatePostfix(String s) {
        String[] terms = s.split("\\s");
        Stack<String> operands = new Stack<String>();
        Stack<String> operators = new Stack<String>();

        for (String term : terms) {
            if (term.equals("+")) {
                double a = Double.parseDouble(operands.pop());
                double b = Double.parseDouble(operands.pop());
                operands.push(String.valueOf(b + a));
            }
            else if (term.equals("-")) {
                double a = Double.parseDouble(operands.pop());
                double b = Double.parseDouble(operands.pop());
                operands.push(String.valueOf(b - a));
            }
            else if (term.equals("*")) {
                double a = Double.parseDouble(operands.pop());
                double b = Double.parseDouble(operands.pop());
                operands.push(String.valueOf(b * a));
            }
            else if (term.equals("/")) {
                double a = Double.parseDouble(operands.pop());
                double b = Double.parseDouble(operands.pop());
                operands.push(String.valueOf(b / a));
            }
            else {
                operands.push(term);
            }
        }

        return operands.toString();
    }

    public static void main(String[] args) {
        String[] infix = {"1 + 2 + 3 + 4 * 5 + 6 + 7 + 8 + 9"
                , "( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )"
                , "( ( 1 + 2 * 3 - 4 ) * ( 5 - 6 ) )"
                , "( 1 + 2 ) * 3 / ( 4 - 5 )"
                , "( 1 + 2 ) * 3 / 4 - 5"
                , "( 1 + 2 ) * 3 - 4 / 5"
        };

        for (String s : infix) {
            StdOut.println("   Infix: " + s);
            StdOut.println(" Postfix: " + Exercise_1_3_10.infixToPostfix(s));
            StdOut.println("Evaluate: " + evaluatePostfix(Exercise_1_3_10.infixToPostfix(s)));
            StdOut.println();
        }

    }
}
