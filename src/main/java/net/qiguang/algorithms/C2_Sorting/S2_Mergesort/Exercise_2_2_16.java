package net.qiguang.algorithms.C2_Sorting.S2_Mergesort;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;
import java.util.Random;

/**
 * 2.2.16 Natural mergesort.
 * Write a version of bottom-up mergesort that takes advantage of order in the array by proceeding as follows
 * each time it needs to ﬁnd two arrays to merge: find a sorted subarray (by incrementing a pointer until finding
 * an entry that is smaller than its predecessor in the array), then ﬁnd the next, then merge them. Analyze the
 * running time of this algorithm in terms of the array size and the number of maximal increasing sequences in the
 * array.
 */
public class Exercise_2_2_16 {
    public static class MergeBUN {
        public static void sort(Comparable[] a) {
            int N = a.length;
            Comparable[] aux = new Comparable[N];
            while (true) {
                for (int lo = 0; lo < N - 2; lo++) {
                    int mid = lo;
                    while (mid < N - 2 && !less(a[mid+1], a[mid]))
                        mid++;
                    int hi = mid + 1;
                    while (hi < N - 1 && !less(a[hi+1], a[hi]))
                        hi++;
                    merge(a, aux, lo, mid, hi);
                    if (hi - lo == N - 1) return;
                    lo = hi;
                }
            }
        }
        public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
            // Merge a[lo..mid] with a[mid+1..hi].
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++)  // Copy a[lo..hi] to aux[lo..hi].
                aux[k] = a[k];
            for (int k = lo; k <= hi; k++) { // Merge back to a[lo..hi].
                if (i > mid) {
                    a[k] = aux[j++];
                } else if (j > hi) {
                    a[k] = aux[i++];
                } else if (less(aux[j], aux[i])) {
                    a[k] = aux[j++];
                }
                else {
                    a[k] = aux[i++];
                }
            }
        }
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }
        public static boolean isSorted(Comparable[] a) {
            for (int i = 1; i < a.length; i++)
                if (less(a[i], a[i-1])) return false;
            return true;
        }
    }

    public static class MergeBU {
        public static void sort(Comparable[] a) {
            int N = a.length;
            Comparable[] aux = new Comparable[N];
            for (int sz = 1; sz < N; sz = sz+sz)
                for (int lo = 0; lo < N-sz; lo += sz+sz)
                    merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
        }
        public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
            // Merge a[lo..mid] with a[mid+1..hi].
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++)  // Copy a[lo..hi] to aux[lo..hi].
                aux[k] = a[k];
            for (int k = lo; k <= hi; k++) { // Merge back to a[lo..hi].
                if (i > mid) {
                    a[k] = aux[j++];
                } else if (j > hi) {
                    a[k] = aux[i++];
                } else if (less(aux[j], aux[i])) {
                    a[k] = aux[j++];
                }
                else {
                    a[k] = aux[i++];
                }
            }
        }
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }
        public static boolean isSorted(Comparable[] a) {
            for (int i = 1; i < a.length; i++)
                if (less(a[i], a[i-1])) return false;
            return true;
        }
    }

    public static void main(String[] args) {
        int trials = 1000;
        int n = 4000;
        Random r = new Random();
        Stopwatch s;

        System.out.printf("%10s %8s %8s\n", "Type", "MergeBU", "MergeBUN");

        // Random arrays
        double ta = 0;
        double tb = 0;
        for (int t = 0; t < trials; t++) {
            Integer[] a = new Integer[n];
            Integer[] b = new Integer[n];
            for (int i = 0; i < n; i++) {
                a[i] = r.nextInt();
                b[i] = a[i];
            }
            // Normal Merge
            s = new Stopwatch();
            MergeBU.sort(a);
            ta += s.elapsedTime();
            // MergeBUN
            s = new Stopwatch();
            MergeBUN.sort(b);
            tb += s.elapsedTime();
        }
        System.out.printf("%10s %8.4f %8.4f\n", "Random", ta, tb);

        // Generally increasing arrays
        ta = 0;
        tb = 0;
        for (int t = 0; t < trials; t++) {
            Integer[] a = new Integer[n];
            Integer[] b = new Integer[n];
            for (int i = 0; i < n; i++) {
                if (i > 0 && r.nextDouble() > 0.2) a[i] = a[i-1] + 1;
                else a[i] = r.nextInt(n);
                b[i] = a[i];
            }
            // Normal Merge
            s = new Stopwatch();
            MergeBU.sort(a);
            ta += s.elapsedTime();
            // MergeBUN
            s = new Stopwatch();
            MergeBUN.sort(b);
            tb += s.elapsedTime();
        }
        System.out.printf("%10s %8.4f %8.4f\n", "Trending", ta, tb);

        // Sorted arrays
        ta = 0;
        tb = 0;
        for (int t = 0; t < trials; t++) {
            Integer[] a = new Integer[n];
            Integer[] b = new Integer[n];
            for (int i = 0; i < n; i++) {
                a[i] = i;
                b[i] = a[i];
            }
            // Normal Merge
            s = new Stopwatch();
            MergeBU.sort(a);
            ta += s.elapsedTime();
            // MergeBUN
            s = new Stopwatch();
            MergeBUN.sort(b);
            tb += s.elapsedTime();
        }
        System.out.printf("%10s %8.4f %8.4f\n", "Sorted", ta, tb);

        // Reversed arrays
        ta = 0;
        tb = 0;
        for (int t = 0; t < trials; t++) {
            Integer[] a = new Integer[n];
            Integer[] b = new Integer[n];
            for (int i = 0; i < n; i++) {
                a[i] = n-i;
                b[i] = a[i];
            }
            // Normal Merge
            s = new Stopwatch();
            MergeBU.sort(a);
            ta += s.elapsedTime();
            // MergeBUN
            s = new Stopwatch();
            MergeBUN.sort(b);
            tb += s.elapsedTime();
        }
        System.out.printf("%10s %8.4f %8.4f\n", "Reversed", ta, tb);

    }
}
