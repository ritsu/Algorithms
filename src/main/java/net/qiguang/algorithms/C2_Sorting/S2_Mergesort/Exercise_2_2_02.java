package net.qiguang.algorithms.C2_Sorting.S2_Mergesort;

import edu.princeton.cs.algs4.Merge;

/**
 * 2.2.2
 * Give traces, in the style of the trace given with Algorithm 2.4, showing how the keys
 * E A S Y Q U E S T I O N are sorted with top-down mergesort.
 *
 * Answer:
 *                           0 1 2 3 4 5 6 7 8 9 10 11
 *                           E A S Y Q U E S T I O N
 *       merge(a, 0, 0,  1)  A E
 *     merge(a, 0, 1,  2)    A E S
 *       merge(a, 3, 3,  4)        Q Y
 *     merge(a, 3, 4,  5)          Q U Y
 *   merge(a, 0, 2,  5)      A E Q S U Y
 *       merge(a, 6, 6,  7)              E S
 *     merge(a, 6,  7,  8)               E S T
 *       merge(a, 9, 9, 10)                    I O
 *     merge(a, 9, 10, 11)                     I N O
 *   merge(a, 6, 8, 11)                  E I N O S T
 * merge(a, 0, 5, 11)        A E E I N O Q S S T U Y
 */
public class Exercise_2_2_02 {
    // For reference
    public static class Merge {
        private static Comparable[] aux;      // auxiliary array for merges

        public static void sort(Comparable[] a) {
            aux = new Comparable[a.length];    // Allocate space just once.
            sort(a, 0, a.length - 1);
        }

        private static void sort(Comparable[] a, int lo, int hi) {
            // Sort a[lo..hi].
            if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;
            sort(a, lo, mid);          // Sort left half.
            sort(a, mid + 1, hi);     // Sort right half.
            merge(a, lo, mid, hi);     // Merge results (code on page 271).
            // Print for exercise
            System.out.printf("merge(a, %2d, %2d, %2d) ", lo, mid, hi);
            for (int i = lo; i <= hi; i++) {
                System.out.printf("%3s", a[i]);
            }
            System.out.println();
        }

        public static void merge(Comparable[] a, int lo, int mid, int hi) {
            // Merge a[lo..mid] with a[mid+1..hi].
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++)  // Copy a[lo..hi] to aux[lo..hi].
                aux[k] = a[k];
            for (int k = lo; k <= hi; k++)  // Merge back to a[lo..hi].
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (less(aux[j], aux[i])) a[k] = aux[j++];
                else a[k] = aux[i++];
        }

        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }
    }

    public static void main(String[] args) {
        String s = "E A S Y Q U E S T I O N";
        String[] a = s.split("\\s+");
        Merge.sort(a);
    }
}
