package net.qiguang.algorithms.C2_Sorting.S1_ElementarySorts;

import java.util.Arrays;

/**
 * 2.1.9
 * Show, in the style of the example trace with Algorithm 2.3, how shellsort sorts the array
 * E A S Y S H E L L S O R T Q U E S T I O N.
 *
 * Answer:
 *                                1 1 1 1 1 1 1 1 1 1 2
 *            0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0
 *     Input  E A S Y S H E L L S O R T Q U E S T I O N
 *   13-sort  E A E S S H E L           Q U S Y T I O N
 *    4-sort  E A E L L H E O N Q I R S S O S T T U S Y
 *    1-sort  A E E E H I L L N O O Q R S S S S T T U Y
 */
public class Exercise_2_1_09 {
    // For reference
    public static class Shell {
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
            int h = 1;
            while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
            while (h >= 1) {
                // h-sort the array.
                for (int i = h; i < N; i++) {
                    // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                    for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                        exch(a, j, j-h);
                }
                h = h/3;
                // Print array for this exercise
                System.out.println(Arrays.toString(a));
            }
        }
    }
    public static void main(String[] args) {
        String s = "E A S Y S H E L L S O R T Q U E S T I O N";
        String[] a = s.split("\\s+");
        Shell.sort(a);
    }
}
