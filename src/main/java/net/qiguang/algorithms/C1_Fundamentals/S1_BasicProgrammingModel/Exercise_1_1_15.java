package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 1.1.15
 * Write a static method histogram() that takes an array a[] of int values and an
 * integer M as arguments and returns an array of length M whose ith entry is the
 * number of times the integer i appeared in the argument array. If the values in
 * a[] are all between 0 and M–1, the sum of the values in the returned array
 * should be equal to a.length.
 */
public class Exercise_1_1_15 {
    private static int[] histogram(int[] a, int m) {
        int[] r = new int[m];
        for (int value : a) {
            if (value <= m) r[value]++;
        }
        return r;
    }

    public static void main(String[] args) {
        // data parameters
        int m = 10;
        int size = 1000;

        // generate data
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = StdRandom.uniform(m);
        }

         // get histogram and print results
        int[] h = histogram(a, m);
        StdOut.printf("Data:\n   %s\n", Arrays.toString(a));
        StdOut.println("Histogram:");
        for (int i = 0; i < h.length; i++) {
            StdOut.printf("%4d: %d\n", i, h[i]);
        }
    }
}
