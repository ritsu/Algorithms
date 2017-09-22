package net.qiguang.algorithms.C2_Sorting.S1_ElementarySorts;

import net.qiguang.algorithms.C1_Fundamentals.S5_CaseStudyUnionFind.Exercise_1_5_18;

import java.util.Arrays;

/**
 * 2.1.1
 * Show, in the style of the example trace with Algorithm 2.1, how selection sort sorts the array
 * E A S Y Q U E S T I O N
 *
 * Answer:
 *  i min  0 1 2 3 4 5 6 7 8 9 10 11
 *         A E S Y Q U E S T I O N
 *  0   1  A E S Y Q U E S T I O N
 *  1   2  A E S Y Q U E S T I O N
 *  2   6  A E S Y Q U E S T I O N
 *  3   9  A E E Y Q U S S T I O N
 *  4  11  A E E I Q U S S T Y O N
 *  5  10  A E E I N U S S T Y O Q
 *  6  11  A E E I N O S S T Y U Q
 *  7   7  A E E I N O Q S T Y U S
 *  8  11  A E E I N O Q S T Y U S
 *  9  11  A E E I N O Q S S Y U T
 * 10  10  A E E I N O Q S S T U Y
 * 11  11  A E E I N O Q S S T U Y
 *         A E E I N O Q S S T U Y
 */
public class Exercise_2_1_01 {
    // For reference
    public static class Selection {
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
            int N = a.length;               // array length
            for (int i = 0; i < N; i++) {
                // Exchange a[i] with smallest entry in a[i+1...N).
                int min = i;                 // index of minimal entry
                for (int j = i+1; j < N; j++)
                    if (less(a[j], a[min])) min = j;
                exch(a, i, min);

                // Print array for this exercise
                System.out.println(Arrays.toString(a));
            }
        }
    }
    public static void main(String[] args) {
        String s = "E A S Y Q U E S T I O N";
        String[] a = s.split("\\s+");
        Selection.sort(a);
    }
}
