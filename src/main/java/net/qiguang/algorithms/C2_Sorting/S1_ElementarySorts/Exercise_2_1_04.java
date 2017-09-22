package net.qiguang.algorithms.C2_Sorting.S1_ElementarySorts;

import java.util.Arrays;

/**
 * 2.1.4
 * Show, in the style of the example trace with Algorithm 2.2,
 * how insertion sort sorts the array E A S Y Q U E S T I O N.
 *
 * Answer:
 *  i  j  0 1 2 3 4 5 6 7 8 9 10 11
 *        E A S Y Q U E S T I O N
 *  1  0  A E
 *  2  2  A E S
 *  3  3  A E S Y
 *  4  2  A E Q S Y
 *  5  4  A E Q S U Y
 *  6  2  A E E Q S U Y
 *  7  5  A E E Q S S U Y
 *  8  6  A E E Q S S T U Y
 *  9  3  A E E I Q S S T U Y
 * 10  4  A E E I O Q S S T U Y
 * 11  4  A E E I N O Q S S T U Y
 */
public class Exercise_2_1_04 {
    // For reference
    public static class Insertion {
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
            for (int i = 1; i < N; i++) {
                // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
                for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                    exch(a, j, j-1);
                // Print array for this exercise
                System.out.println(Arrays.toString(a));
            }
        }
    }
    public static void main(String[] args) {
        String s = "E A S Y Q U E S T I O N";
        String[] a = s.split("\\s+");
        Insertion.sort(a);
    }
}
