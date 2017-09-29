package net.qiguang.algorithms.C2_Sorting.S3_Quicksort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.3.7 Find the expected number of subarrays of size 0, 1, and 2 when quicksort is used to sort an array
 * of N items with distinct keys. If you are mathematically inclined, do the math; if not, run some experiments
 * to develop hypotheses.
 *
 * Answer:
 * size 0: n/10
 * size 1: n/6
 * size 2: n/3
 */
public class Exercise_2_3_07 {
    public static class Quick {
        private static int count0;
        private static int count1;
        private static int count2;

        public static int[] sort(Comparable[] a) {
            StdRandom.shuffle(a);
            count0 = 0;
            count1 = 0;
            count2 = 0;
            sort(a, 0, a.length - 1);
            return new int[] {count0, count1, count2};
        }
        private static void sort(Comparable[] a, int lo, int hi) {
            if      (hi - lo == 0) count0++;
            else if (hi - lo == 1) count1++;
            else if (hi - lo == 2) count2++;
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
                while (less(a[++i], v)) {
                    if (i == hi) break;
                }
                while (less(v, a[--j])) {
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
        System.out.printf("%8s %8s %8s %8s\n", "n", "count0", "count1", "count2");
        for (int n = 100; n <= 100000; n += n) {
            double count0 = 0.0;
            double count1 = 0.0;
            double count2 = 0.0;
            for (int t = 0; t < trials; t++) {
                Integer[] a = new Integer[n];
                for (int i = 0; i < n; i++) {
                    a[i] = i;
                }
                int[] counts = Quick.sort(a);
                count0 += counts[0];
                count1 += counts[1];
                count2 += counts[2];
            }
            System.out.printf("%8d %8.1f %8.1f %8.1f\n", n, count0/trials, count1/trials, count2/trials);
        }
    }
}

