package net.qiguang.algorithms.C1_Fundamentals.S4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 1.4.4
 * Develop a table like the one on page 181 for TwoSum.
 *
 * Answer:
 * statement block   time in seconds   frequency                 total time
 * ------------------------------------------------------------------------------
 * D                 t0                x (depends on input)      t0 * x
 * C                 t1                (N^2)/2 - N/2             t1 * ((N^2)/2 - N/2)
 * B                 t2                N                         t2 * N
 * A                 t3                1                         t3
 *
 *         Grand Total:    t1 / 2 * (N^2)
 *                       + (t2 - t1/2) * N
 *                       + t3 + t0 * x
 * Tilde approximation:  ~ t1 / 2 * (N^2) (assuming x is small)
 *     Order of growth:    N^2
 */
public class Exercise_1_4_04 {

    // Code from http://algs4.cs.princeton.edu/14analysis/TwoSum.java
    // This is here just for reference
    public static class TwoSum {

        // print distinct pairs (i, j) such that a[i] + a[j]  = 0
        public static void printAll(int[] a) {
            int n = a.length;
            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    if (a[i] + a[j] == 0) {
                        StdOut.println(a[i] + " " + a[j]);
                    }
                }
            }
        }

        // return number of distinct pairs (i, j) such that a[i] + a[j] = 0
        public static int count(int[] a) {          // Statement Blocks
            int n = a.length;                       // A
            int count = 0;                          // A
            for (int i = 0; i < n; i++) {           // A B
                for (int j = i+1; j < n; j++) {     // A B C
                    if (a[i] + a[j] == 0) {         // A B C D
                        count++;                    // A B C D
                    }                               // A B C D
                }                                   // A B C
            }                                       // A B
            return count;                           // A
        }

        public static void main(String[] args)  {
            In in = new In(args[0]);
            int[] a = in.readAllInts();
            Stopwatch timer = new Stopwatch();
            int count = count(a);
            StdOut.println("elapsed time = " + timer.elapsedTime());
            StdOut.println(count);
        }
    }
}
