package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.1 Give the value of each of the following expressions:
 *    a. ( 0 + 15 ) / 2
 *    b. 2.0e-6 * 100000000.1
 *    c.  true && false || true && tru
 */
public class Exercise_1_1_01 {
    public static void main(String[] args) {
        // 7
        StdOut.println((0 + 15) / 2);

        // 20.0000002
        StdOut.println(2.0e-6 * 10000000.1);

        // true
        StdOut.println(true && false || true && true);
    }
}
