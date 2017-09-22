package net.qiguang.algorithms.C2_Sorting.S2_Mergesort;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Random;

/**
 * 2.2.6
 * Write a program to compute the exact value of the number of array accesses used by
 * top-down mergesort and by bottom-up mergesort. Use your program to plot the values
 * for N from 1 to 512, and to compare the exact values with the upper bound 6N*lg(N).
 */
public class Exercise_2_2_06 {
    public static class Merge {
        public static int access;             // track array accesses
        private static Comparable[] aux;      // auxiliary array for merges

        public static int sort(Comparable[] a) {
            access = 0;
            aux = new Comparable[a.length];    // Allocate space just once.
            sort(a, 0, a.length - 1);
            return access;
        }

        private static void sort(Comparable[] a, int lo, int hi) {
            // Sort a[lo..hi].
            if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;
            sort(a, lo, mid);          // Sort left half.
            sort(a, mid + 1, hi);     // Sort right half.
            merge(a, lo, mid, hi);     // Merge results (code on page 271).
        }

        public static void merge(Comparable[] a, int lo, int mid, int hi) {
            // Merge a[lo..mid] with a[mid+1..hi].
            int i = lo, j = mid + 1;
            access += (hi-lo+1)*2;
            for (int k = lo; k <= hi; k++)  // Copy a[lo..hi] to aux[lo..hi].
                aux[k] = a[k];
            for (int k = lo; k <= hi; k++) { // Merge back to a[lo..hi].
                if (i > mid) {
                    access += 2;
                    a[k] = aux[j++];
                } else if (j > hi) {
                    access += 2;
                    a[k] = aux[i++];
                } else if (less(aux[j], aux[i])) {
                    access += 4;
                    a[k] = aux[j++];
                }
                else {
                    access += 2;
                    a[k] = aux[i++];
                }
            }
        }

        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }
    }

    public static class MergeBU {
        public static int access;             // track array accesses
        private static Comparable[] aux;      // auxiliary array for merges

        public static int sort(Comparable[] a) {
            access = 0;
            // Do lg N passes of pairwise merges.
            int N = a.length;
            aux = new Comparable[N];
            for (int sz = 1; sz < N; sz = sz+sz) {               // sz: subarray size
                for (int lo = 0; lo < N - sz; lo += sz + sz) {   // lo: subarray index
                    merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
                }
            }
            return access;
        }

        public static void merge(Comparable[] a, int lo, int mid, int hi) {
            // Merge a[lo..mid] with a[mid+1..hi].
            int i = lo, j = mid + 1;
            access += (hi-lo+1)*2;
            for (int k = lo; k <= hi; k++)  // Copy a[lo..hi] to aux[lo..hi].
                aux[k] = a[k];
            for (int k = lo; k <= hi; k++) { // Merge back to a[lo..hi].
                if (i > mid) {
                    access += 2;
                    a[k] = aux[j++];
                } else if (j > hi) {
                    access += 2;
                    a[k] = aux[i++];
                } else if (less(aux[j], aux[i])) {
                    access += 4;
                    a[k] = aux[j++];
                }
                else {
                    access += 2;
                    a[k] = aux[i++];
                }
            }
        }

        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }
    }

    public static void main(String[] args) {
        // Draw
        int height = (int) (6*512*Math.log(512)/Math.log(2));
        StdDraw.setXscale(0, 512);
        StdDraw.setYscale(0, height);

        Random r = new Random();
        for (int n = 0; n <= 512; n++) {
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++) {
                a[i] = r.nextInt();
            }
            int m = Merge.sort(a);
            for (int i = 0; i < n; i++) {
                a[i] = r.nextInt();
            }
            int bu = MergeBU.sort(a);
            double limit = 6*n*Math.log(n)/Math.log(2);

            // Print
            System.out.printf("%4d %6d %6d %6.1f\n", n, m, bu, limit);

            // Draw
            StdDraw.setPenColor(200, 0, 0);
            StdDraw.point(n, m);
            StdDraw.setPenColor(0, 0, 200);
            StdDraw.point(n, bu);
            StdDraw.setPenColor(50, 50, 50);
            StdDraw.point(n, limit);

        }
    }
}
