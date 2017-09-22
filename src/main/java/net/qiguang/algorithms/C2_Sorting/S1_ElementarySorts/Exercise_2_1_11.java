package net.qiguang.algorithms.C2_Sorting.S1_ElementarySorts;

import java.util.Arrays;
import java.util.Random;

/**
 * 2.1.11
 * Implement a version of shellsort that keeps the increment sequence in an array,
 * rather than computing it.
 */
public class Exercise_2_1_11 {
    public static class Shell {
        private static int[] h = {1, 4, 13, 40, 121, 346, 1093};
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }
        private static void exch(Comparable[] a, int i, int j) {
            Comparable t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        public static void sort(Comparable[] a) {
            // Sort a[] into increasing order.
            int N = a.length;
            int idx = 0;
            while (h[idx] < N/3 && idx < h.length-1) idx++;
            while (idx >= 0) {
                // h-sort the array.
                for (int i = h[idx]; i < N; i++) {
                    // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                    for (int j = i; j >= h[idx] && less(a[j], a[j-h[idx]]); j -= h[idx])
                        exch(a, j, j-h[idx]);
                }
                idx--;
            }
        }
    }
    public static void main(String[] args) {
        int n = 20;
        Integer[] a = new Integer[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(n);
        }
        System.out.println(Arrays.toString(a));
        Shell.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
