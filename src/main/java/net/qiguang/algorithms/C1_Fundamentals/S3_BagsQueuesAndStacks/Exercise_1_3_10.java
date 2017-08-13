package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.10
 * Write a filter InfixToPostfix that converts an arithmetic expression from infix to postfix.
 *
 * Examples:
 *    infix:   1 + 2 + 3 + 4 * 5 + 6 + 7 + 8 + 9
 *             ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
 *             ( ( 1 + 2 * 3 - 4 ) * ( 5 - 6 ) )
 *             ( 1 + 2 ) * 3 / ( 4 - 5 )
 *             ( 1 + 2 ) * 3 / 4 - 5
 *             ( 1 + 2 ) * 3 - 4 / 5
 *    postfix: 1 2 + 3 + 4 5 * + 6 + 7 + 8 + 9 +
 *             1 2 + 3 4 - 5 6 - * *
 *             1 2 3 * + 4 - 5 6 - *
 *             1 2 + 3 * 4 5 - /
 *             1 2 + 3 * 4 / 5 -
 *             1 2 + 3 * 4 5 / -
 *
 *  Note: We're assuming the only operators are + - * /
 */
public class Exercise_1_3_10 {
    // Returns TRUE if operator a has higher order of precedence than operator b
    private static boolean isHigherPrecedence(String a, String b) {
        int pa = 0;
        int pb = 0;
        if      (a.equals("-") || a.equals("+")) pa = 0;
        else if (a.equals("*") || a.equals("/")) pa = 1;
        if      (b.equals("-") || b.equals("+")) pb = 0;
        else if (b.equals("*") || b.equals("/")) pb = 1;
        return pa > pb;
    }

    private static boolean isOperator(String s) {
        return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"));
    }

    public static String infixToPostfix(String s) {
        String[] items = s.split("\\s");
        Stack<String> operators = new Stack<String>();
        Stack<String> operands = new Stack<String>();

        for (String item : items) {
            if (isOperator(item)) {
                while (!operators.isEmpty()) {
                    if (operators.peek().equals("("))               break;
                    if (isHigherPrecedence(item, operators.peek())) break;
                    String a = operands.pop();
                    String b = operands.pop();
                    operands.push(b + " " + a + " " + operators.pop());
                }
                operators.push(item);
            }
            else if (item.equals("(")) {
                operators.push(item);
            }
            else if (item.equals(")")) {
                while (!operators.isEmpty()) {
                    if (operators.peek().equals("(")) break;
                    String a = operands.pop();
                    String b = operands.pop();
                    operands.push(b + " " + a + " " + operators.pop());
                }
                operators.pop();
            }
            else {
                operands.push(item);
            }
        }
        while (!operators.isEmpty()) {
            String a = operands.pop();
            String b = operands.pop();
            operands.push(b + " " + a + " " + operators.pop());
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
            StdOut.println("  Infix: " + s);
            StdOut.println("Postfix: " + infixToPostfix(s));
            StdOut.println();
        }
    }
}
