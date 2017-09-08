package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.7
 * Analyze ThreeSum under a cost model that counts arithmetic operations
 * (and comparisons) involving the input numbers.
 *
 * Answer:
 * There is 1 three-way addition for each of the (N^3) / 6 pairs, so (N^3) / 3 addition operations.
 * There is 1 comparison ( == 0 ) for each of the (N^3) / 6 pairs, so (N^3) / 6 comparison operations.
 * Order of growth is still N^3.
 */
public class Exercise_1_4_07 {
    // Brute force implementation taken from text
    public static class ThreeSum {
        public static int count(int[] a) {
            // Count triples that sum to 0.
            int N = a.length;
            int cnt = 0;
            for (int i = 0; i < N; i++)
                for (int j = i + 1; j < N; j++)
                    for (int k = j + 1; k < N; k++) {
                        if (a[i] + a[j] + a[k] == 0) {
                            StdOut.printf("(%11d, %11d, %11d)\n", a[i], a[j], a[k]);
                            cnt++;
                        }
                    }
            return cnt;
        }
    }
}
