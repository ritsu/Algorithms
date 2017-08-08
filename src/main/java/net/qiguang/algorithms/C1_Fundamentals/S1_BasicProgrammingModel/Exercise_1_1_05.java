package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.5
 * Write a code fragment that prints true if the double variables x and y
 * are both strictly between 0 and 1 and false otherwise.
 */
public class Exercise_1_1_05 {
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);

        StdOut.println((x >= 0 && x <= 1) && (y >= 0 && y <= 1));

    }
}
