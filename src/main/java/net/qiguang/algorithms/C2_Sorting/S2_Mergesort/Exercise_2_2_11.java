package net.qiguang.algorithms.C2_Sorting.S2_Mergesort;

import java.util.Arrays;
import java.util.Random;

/**
 * 2.2.11 Improvements.
 * Implement the three improvements to mergesort that are described in the text on page 275:
 *   Add a cutoff for small subarrays,
 *   test whether the array is already in order, and
 *   avoid the copy by switching arguments in the recursive code.
 */
public class Exercise_2_2_11 {
    public static class Merge {
        public static void sort(Comparable[] a) {
            Comparable[] aux = new Comparable[a.length];
            // Copy a to aux here so we can avoid copy in merge
            for (int i = 0; i < a.length; i++) {
                aux[i] = a[i];
            }
            sort(a, aux, 0, a.length - 1, 0);
        }

        private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi, int depth) {
            // Determine whether we are merging onto a or aux
            if (depth %2 == 1) {
                Comparable[] tmp = a;
                a = aux;
                aux = a;
            }
            // Use insertion sort if size less than 16
            if (hi - lo < 16) {
                for (int i = lo + 1; i <= hi; i++) {
                    for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                        Comparable tmp = a[j];
                        a[j] = a[j - 1];
                        a[j - 1] = tmp;
                    }
                }
            } else {
                int mid = lo + (hi - lo) / 2;
                sort(a, aux, lo, mid, depth + 1);
                sort(a, aux, mid + 1, hi, depth + 1);
                // Skip merge if already in order
                if (aux[mid].compareTo(aux[mid + 1]) > 0)
                    merge(a, aux, lo, mid, hi, depth);
            }
        }

        public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi, int depth) {
            // Merge a[lo..mid] with a[mid+1..hi].
            int i = lo, j = mid + 1;
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

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i].compareTo(a[i-1]) < 0) return false;
        return true;
    }

    public static void main(String[] args) {
        // Test a reverse sorted array
        Integer[] nums = new Integer[20];
        for (int i = 0; i < nums.length; i++)
            nums[i] = nums.length - i;
        Merge.sort(nums);
        System.out.println(Arrays.toString(nums));

        // Test random arrays
        Random r = new Random();
        for (int n = 10; n < 1000; n++) {
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++)
                a[i] = r.nextInt(n);
            Merge.sort(a);
            if (!isSorted(a)) System.out.println("We have a problem.");
        }
    }
}
