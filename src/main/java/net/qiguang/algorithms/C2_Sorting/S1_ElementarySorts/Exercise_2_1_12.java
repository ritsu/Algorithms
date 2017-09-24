package net.qiguang.algorithms.C2_Sorting.S1_ElementarySorts;

import java.util.Arrays;
import java.util.Random;

/**
 * 2.1.12
 * Instrument shellsort to print the number of compares divided by the array size for each increment.
 * Write a test client that tests the hypothesis that this number is a small constant, by sorting arrays
 * of random Double values, using array sizes that are increasing powers of 10, starting at 100.
 */
public class Exercise_2_1_12 {
    public static class Shell {
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }
        private static void exch(Comparable[] a, int i, int j) {
            Comparable t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        // Return count of compares for each h-iteration
        public static int[][] sort(Comparable[] a) {
            // Sort a[] into increasing order.
            int N = a.length;
            int h = 1;
            int count = 1;
            while (h < N/3) {
                h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
                count++;
            }
            int[][] ret = new int[2][count];
            int idx = 0;
            while (h >= 1) {
                int size = N/h;
                int compares = 0;
                // h-sort the array.
                for (int i = h; i < N; i++) {
                    // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                    for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                        compares++;
                        exch(a, j, j - h);
                    }
                }
                h = h/3;
                ret[0][idx] = size;
                ret[1][idx] = compares;
                idx++;
            }
            return ret;
        }
    }
    public static void main(String[] args) {
        Random r = new Random();
        for (int n = 100; n < 10000000; n *= 10) {
            Double[] a = new Double[n];
            for (int i = 0; i < n; i++) {
                a[i] = r.nextDouble();
            }
            int[][] stats = Shell.sort(a);
            System.out.println(n);
            for (int size : stats[0]) {
                System.out.printf("%8d", size);
            }
            System.out.println();
            for (int compares : stats[1]) {
                System.out.printf("%8d", compares/n);
            }
            System.out.println();
        }

    }

}

