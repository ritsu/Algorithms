package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.1.26 Sorting three numbers.
 * Suppose that the variables a, b, c, and t are all of the same numeric primitive type.
 * Show that the following code puts a, b, and c in ascending order:
 *
 *     if (a > b) { t = a; a = b; b = t; }
 *     if (a > c) { t = a; a = c; c = t; }
 *     if (b > c) { t = b; b = c; c = t; }
 */
public class Exercise_1_1_26 {
    public static void main(String[] args) {
        // Generate data
        int a = StdRandom.uniform(10);
        int b = StdRandom.uniform(10);
        int c = StdRandom.uniform(10);
        StdOut.println(a + " " + b + " " + c);

        // swap a, b if a > b ... gives a < b
        if (a > b) { int t = a; a = b; b = t; }
        // swap a, c if a > c ... gives a < {b, c}
        if (a > c) { int t = a; a = c; c = t; }
        // swap b, c if b > c ... gives a < b < c
        if (b > c) { int t = b; b = c; c = t; }

        StdOut.println(a + " " + b + " " + c);
    }
}
