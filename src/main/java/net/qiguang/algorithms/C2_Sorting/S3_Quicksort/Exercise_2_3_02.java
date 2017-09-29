package net.qiguang.algorithms.C2_Sorting.S3_Quicksort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * 2.3.2 Show, in the style of the quicksort trace given in this section, how quicksort sorts
 * the array E A S Y Q U E S T I O N (for the purposes of this exercise, ignore the initial shuffle).
 *
 * Answer:
 *                                  1 1
 *   lo  j  hi  0 1 2 3 4 5 6 7 8 9 0 1
 *              E A S Y Q U E S T I O N
 *    0  2  11  E A E Y Q U S S T I O N
 *    0  1   1  A E
 *    0      0  A
 *    3 11  11        N Q U S S T I O Y
 *    3  4  10        I N U S S T Q O
 *    3      3        I
 *    5 10  10            O S S T Q U
 *    5  5   9            O S S T Q U
 *    6  7   9              Q S T S U
 *    6      6              Q
 *    8  9   9                  S T U
 *    8      8                  S
 */
public class Exercise_2_3_02 {
    public static class Quick {
        public static void sort(Comparable[] a) {
            //StdRandom.shuffle(a);
            sort(a, 0, a.length - 1);
        }
        private static void sort(Comparable[] a, int lo, int hi) {
            if (hi <= lo) {
                System.out.printf("%4d %4s %4d %s\n", lo, "-", hi, Arrays.toString(a));
                return;
            }
            int j = partition(a, lo, hi);
            System.out.printf("%4d %4d %4d %s\n", lo, j, hi, Arrays.toString(a));
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
        String[] s = "E A S Y Q U E S T I O N".split("\\s+");
        Quick.sort(s);
    }
}
