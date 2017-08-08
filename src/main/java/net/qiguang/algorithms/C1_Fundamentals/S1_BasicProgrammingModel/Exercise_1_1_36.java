package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.1.36 Empirical shuffle check.
 * Run computational experiments to check that our shuffling code on page 32 works as advertised.
 * Write a program ShuffleTest that takes command-line arguments M and N, does N shuffles of an array of size M
 * that is initialized with a[i] = i before each shuffle, and prints an M-by-M table such that row i gives the
 * number of times i wound up in position j for all j. All entries in the array should be close to N/M.
 */
public class Exercise_1_1_36 {
    public static void shuffle(double[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            // Exchange a[i] with random element in a[i..N-1]
            int r = i + StdRandom.uniform(N-i);
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
