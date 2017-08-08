package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.30 Array exercise.
 * Write a code fragment that creates an N-by-N boolean array a[][] such that a[i][j] is
 * true if i and j are relatively prime (have no common factors), and false otherwise.
 */
public class Exercise_1_1_30 {
    // Generate array
    public static boolean[][] createArray(int n) {
        boolean[][] a = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                a[i][j] = (gcd(i, j) == 1);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                a[i][j] = a[j][i];
            }
        }
        return a;
    }

    // Get gcd of two numbers
    public static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    // Print array
    public static void printArray(boolean[][] a, int n) {
        StdOut.printf("%4s", "");
        for (int i = 0; i < n; i++) {
            StdOut.printf("%4d", i);
        }
        StdOut.println();
        for (int i = 0; i < n; i++) {
            StdOut.printf("%4d", i);
            for (int j = 0; j < n; j++) {
                if (a[i][j]) StdOut.printf("%4s", "*");
                else         StdOut.printf("%4s", "");
            }
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        int n = args.length == 1 ? Integer.parseInt(args[0]) : 10;
        boolean[][] a = createArray(n);
        printArray(a, n);
    }
}
