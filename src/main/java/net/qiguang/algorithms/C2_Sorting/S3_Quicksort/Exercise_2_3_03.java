package net.qiguang.algorithms.C2_Sorting.S3_Quicksort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.3.3 What is the maximum number of times during the execution of Quick.sort()
 * that the largest item can be exchanged, for an array of length N.
 *
 * Answer: lg N
 */
public class Exercise_2_3_03 {
    public static class Quick {
        private static Comparable max;
        private static int maxCount;

        public static int sort(Comparable[] a) {
            StdRandom.shuffle(a);
            max = a[0];
            maxCount = 0;
            for (Comparable c : a) {
                if (less(max, c)) max = c;
            }
            sort(a, 0, a.length - 1);
            return maxCount;
        }
        private static void sort(Comparable[] a, int lo, int hi) {
            if (hi <= lo) {
                //System.out.printf("%4d %4s %4d %s\n", lo, "-", hi, Arrays.toString(a));
                return;
            }
            int j = partition(a, lo, hi);
            //System.out.printf("%4d %4d %4d %s\n", lo, j, hi, Arrays.toString(a));
            sort(a, lo, j - 1);
            sort(a, j + 1, hi);
        }
        private static int partition(Comparable[] a, int lo, int hi) {
            // Partition into a[lo..i-1], a[i], a[i+1..hi].
            int i = lo, j = hi+1;
            Comparable v = a[lo];
            while (true) {
                while (less(a[++i], v)) if (i == hi) break;
                while (less(v, a[--j])) if (j == lo) break;
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
            if (a[i].compareTo(max) == 0 || a[j].compareTo(max) == 0) maxCount++;
            Comparable copy = a[i];
            a[i] = a[j];
            a[j] = copy;
        }
    }
    public static void main(String[] args) {
        int trials = 10000;
        System.out.printf("%6s %4s %6s\n", "n", "max", "lg N");
        for (int n = 100; n < 10000; n += n) {
            int max = 0;
            for (int t = 0; t < trials; t++) {
                Integer[] a = new Integer[n];
                for (int i = 0; i < n; i++) {
                    a[i] = i;
                }
                int cur = Quick.sort(a);
                if (cur > max) max = cur;
            }
            System.out.printf("%6d %4d %6.1f\n", n, max, Math.log(n)/Math.log(2));
        }
    }
}
