package net.qiguang.algorithms.C2_Sorting.S2_Mergesort;

import java.util.Arrays;
import java.util.Random;

/**
 * 2.2.10 Faster merge.
 * Implement a version of merge() that copies the second half of a[] to aux[] in decreasing order and
 * then does the merge back to a[]. This change allows you to remove the code to test that each of the
 * halves has been exhausted from the inner loop. Note: The resulting sort is not stable (see page 341).
 */
public class Exercise_2_2_10 {
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
            int i = lo, j = hi;
            // Copy a[mid+1] .. a[hi] to aux in decreasing order
            for (int k = lo; k <= mid; k++)
                aux[k] = a[k];
            for (int k = hi; k > mid; k--)
                aux[mid + 1 + hi - k] = a[k];
            // Merge from each end
            for (int k = lo; k <= hi; k++) {
                if (less(aux[i], aux[j]))
                    a[k] = aux[i++];
                else
                    a[k] = aux[j--];
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
