package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.14
 * Write a static method lg() that takes an int value N as argument and returns
 * the largest int not larger than the base-2 logarithm of N. Do not use Math.
 */
public class Exercise_1_1_14 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int lg = 0;
        for (int i = 1; i <= n; i *= 2) {
            lg++;
        }
        StdOut.println(--lg);
    }
}
