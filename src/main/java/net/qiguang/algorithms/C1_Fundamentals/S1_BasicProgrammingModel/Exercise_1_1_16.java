package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.16
 * Give the value of exR1(6):
 *     public static String exR1(int n) {
 *         if (n <= 0) return "";
 *         return exR1(n-3) + n + exR1(n-2) + n;
 *     }
 */
public class Exercise_1_1_16 {
    private static String exR1(int n) {
        if (n <= 0) return "";
        return exR1(n-3) + n + exR1(n-2) + n;
    }

    public static void main(String[] args) {
        StdOut.println(exR1(6));

        // e(6)
        // e(3) + 6 + e(4) + 6
        // e(0) + 3 + e(1) + 3 + 6 + e(4) + 6
        // 3 + e(-2) + 1 + e(-1) + 1 + 3 + 6 + e(4) + 6
        // 3 + 1 + 1 + 3 + 6 + e(1) + 4 + e(2) + 4 + 6
        // 3 + 1 + 1 + 3 + 6 + e(-2) + 1 + e(-1) + 1 + 4 + e(2) + 4 + 6
        // 3 + 1 + 1 + 3 + 6 + 1 + 1 + 4 + e(-1) + 2 + e(0) + 2 + 4 + 6
        // 3 + 1 + 1 + 3 + 6 + 1 + 1 + 4 + 2 + 2 + 4 + 6
        // 311361142246
    }
}
