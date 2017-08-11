package net.qiguang.algorithms.C1_Fundamentals.S3_BagsQueuesAndStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.3.5
 * What does the following code fragment print when N is 50?
 * Give a high-level description of what it does when presented with a positive integer N.
 *
 * Answer: Prints the binary representation of N (110010 when N is 50).
 */
public class Exercise_1_3_05 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        int N = 1 + 4 + 16 + 64;
        while (N > 0) {
            stack.push(N % 2);
            N = N / 2;
        }
        for (int d : stack)
            StdOut.print(d);
        StdOut.println();
    }
}
