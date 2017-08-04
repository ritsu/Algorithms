package net.qiguang.algorithms.C1_Fundamentals.S1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.1.11
 * Write a code fragment that prints the contents of a two-dimensional boolean array,
 * using * to represent true and a space to represent false. Include row and column numbers.
 */
public class Exercise_1_1_11 {
    public static void main(String[] args) {
        int rows = Integer.parseInt(args[0]);
        int cols = Integer.parseInt(args[1]);

        boolean[][] a = new boolean[rows][cols];

        // Generate random 2D boolean array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                a[i][j] = (StdRandom.uniform(2) == 0);
            }
        }

        // Header row
        for (int i = 0; i < cols; i++) {
            StdOut.printf("%4d", i);
        }
        StdOut.printf("\n");

        // Data rows
        for (int i = 0; i < rows; i++) {
            StdOut.printf("%-3d", i);
            for (int j = 0; j < cols; j++) {
                if (a[i][j])
                    StdOut.printf("%-4s", "*");
                else
                    StdOut.printf("%-4s", " ");
            }
            StdOut.println();
        }

    }
}
