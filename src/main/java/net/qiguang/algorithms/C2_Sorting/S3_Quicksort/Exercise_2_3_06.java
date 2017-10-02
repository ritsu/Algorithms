package net.qiguang.algorithms.C2_Sorting.S3_Quicksort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.3.6 Write a program to compute the exact value of C_N, and compare the exact value with the
 * approximation 2N ln N, for N = 100, 1,000, and 10,000.
 */
public class Exercise_2_3_06 {
    public static class Quick {
        private static int count;

        public static int sort(Comparable[] a) {
            StdRandom.shuffle(a);
            count = 0;
            sort(a, 0, a.length - 1);
            return count;
        }
        private static void sort(Comparable[] a, int lo, int hi) {
            if (hi <= lo) {
                return;
            }
            int j = partition(a, lo, hi);
            sort(a, lo, j - 1);
            sort(a, j + 1, hi);
        }
        private static int partition(Comparable[] a, int lo, int hi) {
            // Partition into a[lo..i-1], a[i], a[i+1..hi].
            int i = lo, j = hi+1;
            Comparable v = a[lo];
            while (true) {
                count++;
                while (less(a[++i], v)) {
                    count++;
                    if (i == hi) break;
                }
                count++;
                while (less(v, a[--j])) {
                    count++;
                    if (j == lo) break;
                }
                if (i >= j) break;
                exch(a, i, j);
            }
            exch(a, lo, j);
            return j;             // with a[lo..j-1] <= a[j] <= a[j+1..hi].
        }
        private static boolean less(Comparable a, Comparable b) {
            return a.compareTo(b) < 0;
        }
        private static void exch(Comparable[] a, int i, int j) {
            Comparable copy = a[i];
            a[i] = a[j];
            a[j] = copy;
        }
    }
    public static void main(String[] args) {
        int trials = 10000;
        System.out.printf("%8s %8s %8s\n", "n", "cnt", "2N lg N");
        for (int n = 100; n <= 10000; n *= 10) {
            double count = 0.0;
            for (int t = 0; t < trials; t++) {
                Integer[] a = new Integer[n];
                for (int i = 0; i < n; i++) {
                    a[i] = i;
                }
                count += Quick.sort(a);
            }
            System.out.printf("%8d %8.1f %8.1f\n", n, count/trials, 2 * n * Math.log(n)/Math.log(Math.E));
        }
    }
}
