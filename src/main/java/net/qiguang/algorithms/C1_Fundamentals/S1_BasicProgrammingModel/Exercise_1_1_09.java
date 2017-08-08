package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.9
 * Write a code fragment that puts the binary representation of a positive integer N into a String s.
 *
 * Solution: Java has a built-in method Integer.toBinaryString(N) for this job,
 * but the point of the exercise is to see how such a method might be implemented.
 * Here is a particularly concise solution:
 *
 *     String s = "";
 *     for (int n = N; n > 0; n /= 2)
 *         s = (n % 2) + s;
 */
public class Exercise_1_1_09 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        String s = "";
        for (int i = n; i > 0; i = i / 2) {
            s = i % 2 + s;
        }
        StdOut.printf("%d %s\n", n, s);
    }
}
