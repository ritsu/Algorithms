package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.13
 * Write a code fragment to print the transposition (rows and columns changed)
 * of a two-dimensional array with M rows and N columns
 */
public class Exercise_1_1_13 {
    private static void printArray(int[][] a) {
        // Header row
        StdOut.printf("%3s", "");
        for (int i = 0; i < a[0].length; i++) {
            StdOut.printf("%4d", i);
        }
        StdOut.printf("\n");

        // Data rows
        for (int i = 0; i < a.length; i++) {
            StdOut.printf("%3d", i);
            for (int j = 0; j < a[0].length; j++) {
                StdOut.printf("%4d", a[i][j]);
            }
            StdOut.println();
        }
        StdOut.println();
    }

    private static void printArrayTransposed(int[][] a) {
        // Header row
        StdOut.printf("%3s", "");
        for (int i = 0; i < a.length; i++) {
            StdOut.printf("%4d", i);
        }
        StdOut.printf("\n");

        // Data rows
        for (int i = 0; i < a[0].length; i++) {
            StdOut.printf("%3d", i);
            for (int j = 0; j < a.length; j++) {
                StdOut.printf("%4d", a[j][i]);
            }
            StdOut.println();
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        // Get size of array
        int rows = Integer.parseInt(args[0]);
        int cols = Integer.parseInt(args[1]);

        // Generate random array
        int[][] a = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                a[i][j] = StdRandom.uniform(100);
            }
        }

        // Print original array and transposed array
        printArray(a);
        printArrayTransposed(a);
    }
}
