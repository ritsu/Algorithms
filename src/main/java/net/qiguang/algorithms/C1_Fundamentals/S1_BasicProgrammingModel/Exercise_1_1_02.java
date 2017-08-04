package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.2 Give the type and value of each of the following expressions:
 *    a. (1 + 2.236)/2
 *    b. 1 + 2 + 3 + 4.0
 *    c. 4.1 >= 4
 *    d. 1 + 2 + "3"
 */
public class Exercise_1_1_02 {
    public static void main(String[] args) {
        // 1.618 double
        StdOut.println((1 + 2.236)/2);

        // 10.0 double
        StdOut.println(1 + 2 + 3 + 4.0);

        // true boolean
        StdOut.println(4.1 >= 4);

        // 33 string
        StdOut.println(1 + 2 + "3");
    }
}

