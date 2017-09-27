package net.qiguang.algorithms.C2_Sorting.S2_Mergesort;

import java.util.Random;

/**
 * 2.2.19 Inversions.
 * Develop and implement a linearithmic algorithm for computing the number of inversions in a given array
 * (the number of exchanges that would be performed by insertion sort for that array—see Section 2.1).
 * This quantity is related to the  Kendall tau distance; see Section 2.5.
 */
public class Exercise_2_2_19 {
    public static class Merge {
        private static Comparable[] aux;      // auxiliary array for merges

        public static int sort(Comparable[] a) {
            aux = new Comparable[a.length];    // Allocate space just once.
            return sort(a, 0, a.length - 1);
        }

        private static int sort(Comparable[] a, int lo, int hi) {
            // Sort a[lo..hi].
            if (hi <= lo) return 0;
            int mid = lo + (hi - lo) / 2;
            int count = sort(a, lo, mid);          // Sort left half.
            count += sort(a, mid + 1, hi);     // Sort right half.
            count += merge(a, lo, mid, hi);     // Merge results (code on page 271).
            return count;
        }

        public static int merge(Comparable[] a, int lo, int mid, int hi) {
            int count = 0;
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
                    count += (mid - i) + 1;
                }
                else {
                    a[k] = aux[i++];
                }
            }
            return count;
        }

        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }
    }
    public static int inversions(Comparable[] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
                if (a[i].compareTo(a[j]) > 0) count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int n = 20;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        System.out.printf("%4d %4d\n", inversions(a), Merge.sort(a));
        for (int i = 0; i < n; i++) {
            a[i] = n - i;
        }
        System.out.printf("%4d %4d\n", inversions(a), Merge.sort(a));
        Random r = new Random();
        for (int t = 0; t < 10; t++) {
            for (int i = 0; i < n; i++) {
                a[i] = r.nextInt();
            }
            System.out.printf("%4d %4d\n", inversions(a), Merge.sort(a));
        }
    }
}
