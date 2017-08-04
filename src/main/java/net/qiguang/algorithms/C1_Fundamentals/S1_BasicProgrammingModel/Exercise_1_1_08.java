package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.8
 * What do each of the following print?
 *
 *   a. System.out.println('b');
 *   b. System.out.println('b' + 'c');
 *   c. System.out.println((char) ('a' + 4));
 *
 * Explain each outcome
 */
public class Exercise_1_1_08 {
    public static void main(String[] args) {
        // char b
        StdOut.println('b');

        // int value of 'b' + int value of 'c'
        StdOut.println('b' + 'c');

        // char corresponding to int value of a + 4
        StdOut.println((char) ('a' + 4));

    }
}

