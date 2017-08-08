package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.6
 * What does the following program print?
 *
 *   int f = 0;
 *   int g = 1;
 *   for (int i = 0; i <= 15; i++) {
 *       StdOut.println(f);
 *       f = f + g;
 *       g = f - g;
 *   }
 *
 * A: Fibonacci sequence
 *   i  StdOut f  g
 *   0  0      1  0
 *   1  1      1  1
 *   2  1      2  1
 *   3  2      3  2
 *   4  3      5  3
 *   5  5      8  5
 *   ...
 */
public class Exercise_1_1_06 {
    public static void main(String[] args) {
        int f = 0;
        int g = 1;
        for (int i = 0; i <= 15; i++) {
            StdOut.println(f);
            f = f + g;
            g = f - g;
        }

    }
}
