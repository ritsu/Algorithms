package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

/**
 * 1.1.3
 * Write a program that takes three integer command-line arguments and
 * prints equal if all three are equal, and not equal otherwise.
 */
public class Exercise_1_1_03 {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);

        if (a == b && a == c) {
            StdOut.println("equal");
        }
        else {
            StdOut.println("not equal");
        }
    }
}
