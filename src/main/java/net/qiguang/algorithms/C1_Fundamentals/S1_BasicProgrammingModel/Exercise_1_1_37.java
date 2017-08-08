package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.1.37 Bad shuffling.
 * Suppose that you choose a random integer between 0 and N-1 in our shuffling code instead of one between i and N-1.
 * Show that the resulting order is not equally likely to be one of the N! possibilities.
 * Run the test of the previous exercise for this version.
 */
public class Exercise_1_1_37 {
    public static void shuffle(double[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            // Exchange a[i] with random element in a[i..N-1]
            int r = StdRandom.uniform(N);
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void main(String[] args) {
        int m = args.length >= 1 ? Integer.parseInt(args[0]) : 10;
        int n = args.length >= 2 ? Integer.parseInt(args[1]) : 5000;

        double[] a = new double[m];
        int[][] stats = new int[m][m];

        // Run test
        for (int i = 0; i < n; i++) {
            // Initialize
            for (int j = 0; j < m; j++) {
                a[j] = j;
            }
            // Shuffle
            shuffle(a);
            // Update stats
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    if (a[j] == k) stats[j][k]++;
                }
            }
        }

        // Print results
        StdOut.printf("%5s", "");
        for (int i = 0; i < m; i++) {
            StdOut.printf("%4d", i);
        }
        StdOut.println();
        for (int i = 0; i < m; i++) {
            StdOut.printf("%4d:", i);
            for (int j = 0; j < m; j++) {
                StdOut.printf("%4d", stats[i][j]);
            }
            StdOut.println();
        }

    }
}
