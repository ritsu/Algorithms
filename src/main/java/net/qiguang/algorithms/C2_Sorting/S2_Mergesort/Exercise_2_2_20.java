package net.qiguang.algorithms.C2_Sorting.S2_Mergesort;

import java.util.Arrays;
import java.util.Random;

/**
 * 2.2.20 Indirect sort.
 * Develop and implement a version of mergesort that does not rearrange the array, but returns an int[] array perm
 * such that perm[i] is the index of the i th smallest entry in the array.
 */
public class Exercise_2_2_20 {
    public static class Merge {
        private static Comparable[] aux;      // auxiliary array for merges
        private static int[] perm;
        private static int[] auxp;

        public static int[] sort(Comparable[] a) {
            aux = new Comparable[a.length];    // Allocate space just once.
            perm = new int[a.length];
            auxp = new int[a.length];
            for (int i = 0; i < a.length; i++)
                perm[i] = i;
            sort(a,0, a.length - 1);
            return perm;
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
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                aux[k] = a[perm[k]];
                auxp[k] = perm[k];
            }
            for (int k = lo; k <= hi; k++) { // Merge back to a[lo..hi].
                if (i > mid) {
                    perm[k] = auxp[j++];
                } else if (j > hi) {
                    perm[k] = auxp[i++];
                } else if (less(aux[j], aux[i])) {
                    perm[k] = auxp[j++];
                }
                else {
                    perm[k] = auxp[i++];
                }
            }
        }

        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }
    }
    public static void main(String[] args) {
        int n = 20;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        System.out.println(Arrays.toString(Merge.sort(a)));
        System.out.println(Arrays.toString(a));
        System.out.println();
        for (int i = 0; i < n; i++) {
            a[i] = n - 1 - i;
        }
        System.out.println(Arrays.toString(Merge.sort(a)));
        System.out.println(Arrays.toString(a));
        System.out.println();
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(n);
        }
        System.out.println(Arrays.toString(Merge.sort(a)));
        System.out.println(Arrays.toString(a));

    }
}
