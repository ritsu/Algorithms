package net.qiguang.algorithms.C1_Fundamentals.S2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.2.6
 * A string s is a circular rotation of a string t if it matches when the characters are circularly shifted
 * by any number of positions; e.g., ACTGACG is a circular shift of TGACGAC, and vice versa.
 * Detecting this condition is important in the study of genomic sequences.
 * Write a program that checks whether two given strings s and t are circular shifts of one another.
 * Hint : The solution is a one-liner with indexOf(), length(), and string concatenation.
 */
public class Exercise_1_2_06 {
    public static boolean isCircular(String a, String b) {
        return (a + a).contains(b) && a.length() == b.length();
    }

    public static void main (String[] args) {
        String s1 = "ACTGACG";
        String s2 = "TGACGAC";
        String s3 = "TGACGA";

        StdOut.println(isCircular(s1, s2));
        StdOut.println(isCircular(s2, s1));
        StdOut.println(isCircular(s1, s1));
        StdOut.println(isCircular(s1, s3));
    }
}
