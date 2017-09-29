package net.qiguang.algorithms.C2_Sorting.S3_Quicksort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.3.1 Show, in the style of the trace given with partition(),
 * how that method partitions the array E A S Y Q U E S T I O N.
 *
 * Answer:
 *                                       1 1
 *             i  j  0 1 2 3 4 5 6 7 8 9 0 1
 *     initial 0 12  E A S Y Q U E S T I O N
 *        scan 2  6    A S       E S T I O N
 *        exch 2  6      E       S
 *        scan 3  2        Y Q U
 *  final exch 3  2  E   E
 *      result    2  E A E Y Q U S S T I O N
 */
public class Exercise_2_3_01 {
    public static class Quick {
        public static void sort(Comparable[] a) {
            StdRandom.shuffle(a);
            sort(a, 0, a.length - 1);
        }
        private static void sort(Comparable[] a, int lo, int hi) {
            if (hi <= lo) return;
            int j = partition(a, lo, hi);
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
            Comparable copy = a[i];
            a[i] = a[j];
            a[j] = copy;
        }
    }
    public static void main(String[] args) {

    }
}
