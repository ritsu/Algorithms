package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;

/**
 * 1.1.39 Random matches.
 * Write a BinarySearch client that takes an int value T as command-line argument and runs
 * T trials of the following experiment for N = 103, 104, 105, and 106:
 * generate two arrays of N randomly generated positive six-digit int values,
 * and ﬁnd the number of values that appear in both arrays.
 * Print a table giving the average value of this quantity over the T trials for each value of N.
 */
public class Exercise_1_1_39 {
    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1);
    }
    public static int rank(int key, int[] a, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if      (key < a[mid]) return rank(key, a, lo, mid - 1);
        else if (key > a[mid]) return rank(key, a, mid + 1, hi);
        else                   return mid;
    }

    public static void main(String[] args) {
        int t = args.length == 1 ? Integer.parseInt(args[0]) : 100000;
        int[] n = {103, 104, 105, 106};
        int[][] m = new int[n.length][t];

        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < t; j++) {
                int[] a = new int[n[i]];
                int[] b = new int[n[i]];
                for (int k = 0; k < n[i]; k++) {
                    a[k] = StdRandom.uniform(100000, 1000000);
                    b[k] = StdRandom.uniform(100000, 1000000);
                }
                Arrays.sort(a);
                Arrays.sort(b);
                for (int k = 0; k < a.length; k++) {
                    if (rank(a[k], b) > -1) {
                        m[i][j]++;
                    }
                }
            }
        }

        for (int i = 0; i < n.length; i++) {
            StdOut.printf("%4d:", n[i]);
            double sum = 0.0;
            for (int j = 0; j < m[i].length; j++) {
                sum += m[i][j];
            }
            StdOut.printf("%8.4f\n", sum / m[i].length);
        }
    }
}
