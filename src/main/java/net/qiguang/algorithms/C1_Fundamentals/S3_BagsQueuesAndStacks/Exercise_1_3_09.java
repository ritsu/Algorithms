package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.3.9
 * Write a program that takes from standard input an expression without left parentheses and prints the
 * equivalent infix expression with the parentheses inserted. For example, given the input:
 *    1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
 * your program should print:
 *    ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) )
 *
 * Note: We assume there was a typo in the text, and the program should instead print:
 *    ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
 */
public class Exercise_1_3_09 {
    public static void main (String[] args) {
        // Sample input
        String input = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
        StdOut.println(" Input: " + input);
        String[] items = input.split("\\s");

        // Stacks
        Stack<String> operands = new Stack<String>();
        Stack<String> operators = new Stack<String>();

        // Output
        StdOut.printf("Output: ");
        for (String item : items) {
            if (item.equals(")")) {
                String s3 = operands.pop();
                String s2 = operators.pop();
                String s1 = operands.pop();
                operands.push("( " + s1 + " " + s2 + " " + s3 + " )");
            }
            else if (item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/")) {
                operators.push(item);
            }
            else {
                operands.push(item);
            }
        }
        StdOut.println(operands.pop());

    }
}
