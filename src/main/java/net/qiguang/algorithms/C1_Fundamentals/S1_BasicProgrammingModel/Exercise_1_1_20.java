package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import java.lang.Math;

/**
 * 1.1.20
 * Write a recursive static method that computes the value of ln(N!)
 */
public class Exercise_1_1_20 {
    private static double lnf(int n) {
        if (n == 1) return 0;
        return Math.log(n) + lnf(n-1);
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 30; i++) {
            StdOut.printf("%3d: %f\n", i, lnf(i));
        }
    }
}
