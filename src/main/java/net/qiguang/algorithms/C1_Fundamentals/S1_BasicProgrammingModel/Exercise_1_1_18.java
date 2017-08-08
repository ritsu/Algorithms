package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.18
 * Consider the following recursive function:
 *     public static int mystery(int a, int b) {
 *         if (b == 0) return 0;
 *         if (b % 2 == 0) return mystery(a+a, b/2);
 *         return mystery(a+a, b/2) + a;
 *     }
 *
 * What are the values of mystery(2, 25) and mystery(3, 11)? Given positive integers a and b,
 * describe what value mystery(a, b) computes. Answer the same question, but replace + with *
 * and replace return 0 with return 1.
 */
public class Exercise_1_1_18 {
    // multiply function
    public static int mystery(int a, int b) {
        if (b == 0)     return 0;
        if (b % 2 == 0) return mystery(a+a, b/2);
        return mystery(a+a, b/2) + a;
    }

    // power function
    public static int mystery2(int a, int b) {
        if (b == 0)     return 1;
        if (b % 2 == 0) return mystery2(a*a, b/2);
        return mystery2(a*a, b/2) * a;
    }

    public static void main(String[] args) {
        StdOut.println(mystery(2, 25));
        StdOut.println(mystery(3, 11));

        StdOut.println(mystery2(2, 10));
        StdOut.println(mystery2(3, 5));
    }
}
