package net.qiguang.algorithms.C2_Sorting.S2_Mergesort;

import java.util.Arrays;
import java.util.Random;

/**
 * 2.2.9
 * Use of a static array like aux[] is inadvisable in library software because multiple clients
 * might use the class concurrently. Give an implementation of Merge that does not use a static
 * array. Do not make aux[] local to merge() (see the Q&A for this section).
 *
 * Hint : Pass the auxiliary array as an argument to the recursive sort().
 */
public class Exercise_2_2_09 {
    public static class Merge {
        public static void sort(Comparable[] a) {
            Comparable[] aux = new Comparable[a.length];    // Allocate space just once.
            sort(a, aux, 0, a.length - 1);
        }

        private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
            // Sort a[lo..hi].
            if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;
            sort(a, aux, lo, mid);          // Sort left half.
            sort(a, aux, mid + 1, hi);     // Sort right half.
            merge(a, aux, lo, mid, hi);     // Merge results (code on page 271).
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
    }

    public static void main(String[] args) {
        int n = 50;
        Integer[] a = new Integer[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(n);
        }
        Merge.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
